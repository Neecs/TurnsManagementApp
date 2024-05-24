package edu.uptc.swii.NotificationService.service;

public interface IEmailService {

    void sendEmail(String toUser, String subject, String message);

}
