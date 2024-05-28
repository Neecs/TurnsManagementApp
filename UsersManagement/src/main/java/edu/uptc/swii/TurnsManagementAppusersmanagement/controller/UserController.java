package edu.uptc.swii.TurnsManagementAppusersmanagement.controller;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import edu.uptc.swii.TurnsManagementAppusersmanagement.model.UserDTO;
import edu.uptc.swii.TurnsManagementAppusersmanagement.model.UserIdRequest;
import edu.uptc.swii.TurnsManagementAppusersmanagement.service.IKeycloakService;
import edu.uptc.swii.TurnsManagementAppusersmanagement.service.UserItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IKeycloakService keycloakService;

    @Autowired
    private UserItemService userItemService;


    @Operation(summary = "Department Details",  description = "Retrieves the details of a Department by DepartmentID",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode="200", description ="Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping("/allUsers")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        String response = keycloakService.createUser(userDTO);
        return ResponseEntity.created(new URI("/create")).body(response);
    }

    @PostMapping("/store")
    public String storeNewUser(@RequestBody UserItem userItem) {
        userItemService.save(userItem);
        return "User stored succesfully";
    }

    @PostMapping("/checkExistence")
    public boolean checkUserStorage(@RequestBody UserIdRequest userIdRequest) {
        String userId = userIdRequest.getUserId();
        return userItemService.checkUserExistenceInDb(userId);
    }

    @GetMapping("/getUsersRole")
    public ResponseEntity<?> findUsers(){
        return ResponseEntity.ok(keycloakService.findUsers());
    }

    @GetMapping("/getUserOrganizationDependents")
    public List<UserItem> userOrganizationAdmins(@RequestBody UserIdRequest userIdRequest){
        return userItemService.getAdminsByOrganization(userIdRequest.getUserId());
    }

    @GetMapping("/getUserOrganizationClients")
    public List<UserItem> userOrganizationClients(@RequestBody UserIdRequest userIdRequest){
        return userItemService.getUsersByOrganization(userIdRequest.getUserId());
    }
}
