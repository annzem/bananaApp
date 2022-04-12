package com.github.annzem.banana.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.annzem.banana.protocol.TgMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

//@Component
public class BotConnector {

    @Value("${com.company.bananabot.broker.inputqueue}")
    private String botQueue;

    private final ObjectMapper objectMapper;

    private final JmsTemplate jmsTemplate;

    public BotConnector(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "${com.company.bananaapp.broker.inputqueue}")
    @SendTo("${com.company.bananabot.broker.inputqueue}")
    public TgMessage receiveAndForwardMessageFromQueue(final TgMessage tgMessage) throws JMSException {


        System.out.println("Received message " + tgMessage);

        //new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(messageData);

//            textMessage.setStringProperty("CamelTelegramChatId", chatId);
//            jmsTemplate.convertAndSend(botQueue, textMessage.getText(), new MessagePostProcessor() {
//                @Override
//                public Message postProcessMessage(Message message) throws JMSException {
//                    message.setStringProperty("CamelTelegramChatId", chatId);
//                    return message;
//                }
//            });
        return new TgMessage(tgMessage.getText() + "qweqweqw", tgMessage.getChatId());

    }
}
