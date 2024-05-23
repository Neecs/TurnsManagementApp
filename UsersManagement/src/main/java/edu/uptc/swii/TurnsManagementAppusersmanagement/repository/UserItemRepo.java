package edu.uptc.swii.TurnsManagementAppusersmanagement.repository;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepo  extends JpaRepository<UserItem, Integer>{
    UserItem save (UserItem userItem);

    boolean existsById(String id);
}
