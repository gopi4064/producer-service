//package com.gk.ms.hs.producer_service.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//
//
//
//public class MessageService {
//
//    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
//
//    private static final String QUEUE_NAME = "sample-queue";
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public void sendMessage(String message) {
//        try {
//            jmsTemplate.convertAndSend(QUEUE_NAME, message);
//            logger.info("Message sent successfully: {}", message);
//        } catch (Exception e) {
//            logger.error("Failed to send message: {}", message, e);
//        }
//    }
//}
