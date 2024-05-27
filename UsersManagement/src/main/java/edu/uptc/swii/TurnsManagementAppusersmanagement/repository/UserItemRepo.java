package edu.uptc.swii.TurnsManagementAppusersmanagement.repository;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserItemRepo  extends JpaRepository<UserItem, String>{
    UserItem save (UserItem userItem);

    boolean existsById(String id);

    @Query("SELECT email FROM UserItem WHERE id = ?1")
    String findEmailById(String userId);

    @Query("SELECT organization FROM UserItem WHERE id = ?1")
    String getUserOrganization(String userId);

    @Query("SELECT u FROM UserItem u WHERE u.organization = ?1")
    List<UserItem> getUsersByOrganization(String organization);

}
