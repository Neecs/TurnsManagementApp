package edu.uptc.swii.NotificationService.domain;

public record EmailDTO(String[] toUser,
                       String subject,
                       String message) {
}
