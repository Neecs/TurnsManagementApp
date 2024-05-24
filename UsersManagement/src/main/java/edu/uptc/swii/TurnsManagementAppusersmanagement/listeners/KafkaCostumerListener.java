package edu.uptc.swii.TurnsManagementAppusersmanagement.listeners;

import edu.uptc.swii.TurnsManagementAppusersmanagement.service.UserItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
public class KafkaCostumerListener {

    @Autowired
    UserItemService userItemService;

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaCostumerListener.class);


    @KafkaListener(topics = {"Turns-attended-soon-topic"}, groupId = "groupId")
    public void listener(String message){
        LOGGER.info("Mensaje recibido: " + message);
        System.out.println(userItemService.getUserEmailById("9244e329-8737-4a91-9ec3-7fc52e7a2680"));
    }
}
