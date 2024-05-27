package edu.uptc.swii.TurnsService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
@Data
public class Turn {
    @Id
    private String id;
    private String userId;
    private String dependentId;
    private LocalDateTime scheduledDate;
    private Boolean emailSended;

    public Turn() {
        this.emailSended = false;
    }
}
