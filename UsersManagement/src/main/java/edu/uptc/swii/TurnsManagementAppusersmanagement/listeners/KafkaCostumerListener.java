package edu.uptc.swii.TurnsManagementAppusersmanagement.listeners;

import edu.uptc.swii.TurnsManagementAppusersmanagement.service.UserItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;


@Configuration
public class KafkaCostumerListener {

    @Autowired
    UserItemService userItemService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaCostumerListener.class);


    @KafkaListener(topics = {"User-id-to-be-attended"}, groupId = "groupId")
    public void listener(String userId){
        LOGGER.info("Correo del id de usuario recibido: " + userId);
        String userEmail = userItemService.getUserEmailById(userId);
        System.out.println(userEmail);
        kafkaTemplate.send("User-email-to-be-attended", "UserEmail", userEmail);
    }
}
