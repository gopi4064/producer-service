package com.gk.ms.hs.producer_service.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigMetadata {

    private static final Logger log = LoggerFactory.getLogger(ConfigMetadata.class);
    private static final String CONFIG_QUEUE_NAME = "config-queue";
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void publishMetadata() {
        // Create metadata to publish
        Map<String, String> metadata = new HashMap<>();
        metadata.put("applicationName", "MyApplication");
        metadata.put("version", "1.0.0");
        metadata.put("status", "active");
        metadata.put("timestamp", String.valueOf(System.currentTimeMillis()));

        // Publish the metadata to the configuration queue
        sendMetadataToQueue(metadata);
    }

    public void sendMetadataToQueue(Map<String, String> metadata) {
        try {
            // Publish metadata as a text message (e.g., JSON or key-value pairs)
            String metadataMessage = metadata.toString(); // or serialize to JSON, if needed
            jmsTemplate.convertAndSend(CONFIG_QUEUE_NAME, metadataMessage);
            log.info("Successfully sent metadata to queue: {}", CONFIG_QUEUE_NAME);
        } catch (Exception e) {
            log.error("Error sending metadata to queue: {}", CONFIG_QUEUE_NAME, e);
        }
    }
}
