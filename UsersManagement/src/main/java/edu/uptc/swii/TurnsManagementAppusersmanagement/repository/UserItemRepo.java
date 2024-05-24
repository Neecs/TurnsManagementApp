package edu.uptc.swii.TurnsManagementAppusersmanagement.repository;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserItemRepo  extends JpaRepository<UserItem, String>{
    UserItem save (UserItem userItem);

    boolean existsById(String id);

    @Query("SELECT email FROM UserItem WHERE id = ?1")
    String findEmailById(String userId);
}
