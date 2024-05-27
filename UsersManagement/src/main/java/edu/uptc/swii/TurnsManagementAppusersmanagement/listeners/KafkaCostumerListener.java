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


    @KafkaListener(topics = {"User-id-to-be-attended"}, groupId = "groupId")
    public void listener(String userId){
        LOGGER.info("Correo del id de usuario recibido: " + userId);
        System.out.println(userItemService.getUserEmailById("578a536a-995d-4858-b711-fdb9d719f0be"));
    }
}
