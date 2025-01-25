package com.gk.ms.hs.producer_service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gk.ms.hs.producer_service.model.Pain008;
import com.gk.ms.hs.producer_service.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;

@Component

public class MessageListener {


    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @JmsListener(destination = "sample-queue")
    @JmsListener(destination = "new")
    @JmsListener(destination = "config-queue")

    public void listen(String message) {

        log.info("Received message: {}", message);
    }


    @JmsListener(destination = "LQ.DS.CON.PAIN008")
    public void listen2(Pain008 rawMessage) {
        try {
            log.info("Raw message received: {}", rawMessage.toString());

           // ObjectMapper mapper = new ObjectMapper();
         //   Pain008 pain008 = mapper.readValue(rawMessage, Pain008.class);
           // log.info("Deserialized message: {}", pain008.toString());
        } catch (Exception e) {
            log.error("Error deserializing message: {}", e.getMessage());
            log.error("Raw message: {}", rawMessage);
            throw new RuntimeException("Failed to process message", e);
        }
    }

    @JmsListener(destination = "testmessage")
    public void messageListener(SystemMessage systemMessage) {
      log.info("Message received! {}", systemMessage.toString());
        System.out.println(systemMessage);
    }



}
