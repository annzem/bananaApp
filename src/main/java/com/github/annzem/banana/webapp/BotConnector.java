package com.github.annzem.banana.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.annzem.banana.protocol.TgMessage;
import com.github.annzem.banana.webapp.model.Token;
import com.github.annzem.banana.webapp.model.User;
import com.github.annzem.banana.webapp.model.repository.UserRepository;
import com.github.annzem.banana.webapp.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.Optional;

@Component
public class BotConnector {

    private static Logger logger = LoggerFactory.getLogger(BotConnector.class);

    private final UserRepository userRepository;
    private final AuthService authService;

    @Value("${com.company.bananabot.broker.inputqueue}")
    private String botQueue;

    private final ObjectMapper objectMapper;

    private final JmsTemplate jmsTemplate;

    public BotConnector(ObjectMapper objectMapper, JmsTemplate jmsTemplate, UserRepository userRepository, AuthService authService) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @JmsListener(destination = "${com.company.bananaapp.broker.inputqueue}")
    @SendTo("${com.company.bananabot.broker.inputqueue}")
    public TgMessage receiveAndForwardMessageFromQueue(final TgMessage tgMessage) throws JMSException {

        Optional<User> optionalUser = userRepository.findByChatId(tgMessage.getChatId());

        if (optionalUser.isEmpty()) {
            optionalUser = Optional.of(new User(tgMessage.getChatId()));
            userRepository.saveAndFlush(optionalUser.get());
        }

        Token token = authService.createTokenFor(optionalUser.get());

        logger.debug("Received message {}", tgMessage);

        return new TgMessage("http://192.168.100.180:8080/token?token_val=" + token.getTokenVal(), tgMessage.getChatId());
    }
//
//    @Autowired
//    public AuditorAware auditorAware;
//
//    @SendTo("${com.company.bananabot.broker.inputqueue}")
//    public TgMessage sendMsgToQueue(final TgMessage tgMessage) {
//        Optional <String> userId = (auditorAware.getCurrentAuditor().get()).
//        User user = (User) auditorAware.getCurrentAuditor().get();
//
//
//        logger.debug("Received message {}", tgMessage);
//
//        return new TgMessage("http://192.168.100.180:8080/token?token_val=" + token.getTokenVal(), tgMessage.getChatId());
//    }
}
