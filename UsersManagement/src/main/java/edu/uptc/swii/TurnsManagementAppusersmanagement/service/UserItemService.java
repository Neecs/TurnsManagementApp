package edu.uptc.swii.TurnsManagementAppusersmanagement.service;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import edu.uptc.swii.TurnsManagementAppusersmanagement.repository.UserItemRepo;
import edu.uptc.swii.TurnsManagementAppusersmanagement.service.impl.KeycloakServiceImpl;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserItemService {
    private final UserItemRepo userItemRepo;

    @Autowired
    @Lazy
    KeycloakServiceImpl keycloakService;

    public UserItemService(UserItemRepo userItemRepo) {
        this.userItemRepo = userItemRepo;
    }

    public void save(UserItem userItem) {
        userItemRepo.save(userItem);
    }

    public boolean checkUserExistenceInDb(String userID) {
        return userItemRepo.existsById(userID);
    }

    public String getUserEmailById(String userId) {
        return userItemRepo.findEmailById(userId);
    }

    public List<UserItem> getAdminsByOrganization(String userId) {
        String organization = userItemRepo.getUserOrganization(userId);
        List<UserRepresentation> allAdmins = keycloakService.findAdmins();
        List<UserItem> organizationUsers = userItemRepo.getUsersByOrganization(organization);
        List<UserItem> organizationAdmins= new ArrayList<>();
        List<String> allAdminsId = new ArrayList<>();

        for(UserRepresentation user: allAdmins){
            allAdminsId.add(user.getId());
        }

        for (UserItem organizationUser: organizationUsers){
            if(allAdminsId.contains(organizationUser.getId())){
                 organizationAdmins.add(organizationUser);
            }
        }

        return organizationAdmins;
    }

    public List<UserItem> getUsersByOrganization(String userId) {
        String organization = userItemRepo.getUserOrganization(userId);
        List<UserRepresentation> allUsers = keycloakService.findUsers();
        List<UserItem> organizationUsers = userItemRepo.getUsersByOrganization(organization);
        List<UserItem> organizationUsersRole= new ArrayList<>();
        List<String> allUsersId = new ArrayList<>();

        for(UserRepresentation user: allUsers){
            allUsersId.add(user.getId());
        }

        for (UserItem organizationUser: organizationUsers){
            if(allUsersId.contains(organizationUser.getId())){
                organizationUsersRole.add(organizationUser);
            }
        }

        return organizationUsersRole;
    }
}
