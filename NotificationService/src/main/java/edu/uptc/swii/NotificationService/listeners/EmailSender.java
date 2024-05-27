package edu.uptc.swii.NotificationService.listeners;

import edu.uptc.swii.NotificationService.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class EmailSender {

    @Autowired
    IEmailService emailService;



    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);


    @KafkaListener(topics = {"User-email-to-be-attended"}, groupId = "groupId")
    public void listener(String userEmail) {
        LOGGER.info("Correo del id de usuario recibido: " + userEmail);
        emailService.sendEmail(userEmail, "piripiti","hola pana" );
    }
}
