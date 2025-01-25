package com.gk.ms.hs.producer_service.controller;


import com.gk.ms.hs.producer_service.config.ActiveMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private static final String QUEUE_NAME = "sample-queue";

    @Autowired
    private JmsTemplate jmsTemplate;


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage( @RequestParam String queue,@RequestParam String message) {


        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }

        try {
            jmsTemplate.convertAndSend(queue,message);
            return ResponseEntity.ok("Message sent to queue " + message);
        } catch (Exception e) {
            log.error("Failed to send message", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message to queue " );
        }
    }


}
