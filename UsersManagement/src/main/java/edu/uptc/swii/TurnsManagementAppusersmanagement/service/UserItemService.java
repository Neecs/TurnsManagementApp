package edu.uptc.swii.TurnsManagementAppusersmanagement.service;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import edu.uptc.swii.TurnsManagementAppusersmanagement.repository.UserItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserItemService {
    private final UserItemRepo userItemRepo;

    public UserItemService(UserItemRepo userItemRepo) {
        this.userItemRepo = userItemRepo;
    }

    public void save (UserItem userItem){
        userItemRepo.save(userItem);
    }

    public boolean checkUserExistenceInDb(String userID){
        return userItemRepo.existsById(userID);
    }

}
