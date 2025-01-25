package com.gk.ms.hs.producer_service.controller;


import com.gk.ms.hs.producer_service.model.Pain008;
import com.gk.ms.hs.producer_service.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);
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


    @PostMapping("/send/pain008")
    public ResponseEntity<String> sendMessage(@RequestBody Pain008 pain008) {
        if (pain008 == null || pain008.toString().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        try {
            jmsTemplate.convertAndSend("LQ.DS.CON.PAIN008", pain008);
            log.info("Message sent to queue: {}", pain008);
            return ResponseEntity.ok("Message sent to queue: " + pain008);
        } catch (Exception e) {
            log.error("Failed to send message", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message to queue");
        }
    }

    @PostMapping("/publishMessage")
    public ResponseEntity<String> publishMessage(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("testmessage", systemMessage);

            return new ResponseEntity<>("Sent.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
