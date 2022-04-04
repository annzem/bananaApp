package com.company.bananaapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class BotConnector {

    private final ObjectMapper objectMapper;

    public BotConnector(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "${com.company.bananaapp.broker.inputqueue}")
    @SendTo("${com.company.bananabot.broker.inputqueue}")
    public String receiveAndForwardMessageFromQueue(final Message jsonMessage) throws JMSException {
        String messageData = null;

        System.out.println("Received message " + jsonMessage);
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            System.out.println("messageData:"+messageData);
            //new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(messageData);

        }
        return messageData;
    }
}
