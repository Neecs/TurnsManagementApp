package edu.uptc.swii.NotificationService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {


    public ResponseEntity<?> receiveRequestEmail(){



        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
