package edu.uptc.swii.TurnsManagementAppusersmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserItem {
    @Id
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String organization;

}
