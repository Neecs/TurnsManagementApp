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

    @Operation(summary = "Retrieve All Users", description = "Retrieves all users from Keycloak",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/allUsers")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @Operation(summary = "Create User", description = "Creates a new user in Keycloak")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        String response = keycloakService.createUser(userDTO);
        return ResponseEntity.created(new URI("/create")).body(response);
    }

    @Operation(summary = "Store New User", description = "Stores a new user in the local database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User stored successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/store")
    public String storeNewUser(@RequestBody UserItem userItem) {
        userItemService.save(userItem);
        return "User stored successfully";
    }

    @Operation(summary = "Check User Existence", description = "Checks if a user exists in the local database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User existence checked successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/checkExistence")
    public boolean checkUserStorage(@RequestBody UserIdRequest userIdRequest) {
        String userId = userIdRequest.getUserId();
        return userItemService.checkUserExistenceInDb(userId);
    }

    @Operation(summary = "Get Users Role", description = "Retrieves users and their roles from Keycloak",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users and their roles", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/getUsersRole")
    public ResponseEntity<?> findUsers() {
        return ResponseEntity.ok(keycloakService.findUsers());
    }

    @Operation(summary = "Get Organization Admins", description = "Retrieves organization administrators by organization ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved organization admins", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/getUserOrganizationDependents")
    public List<UserItem> userOrganizationAdmins(@RequestBody UserIdRequest userIdRequest) {
        return userItemService.getAdminsByOrganization(userIdRequest.getUserId());
    }

    @Operation(summary = "Get Organization Clients", description = "Retrieves organization clients by organization ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved organization clients", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/getUserOrganizationClients")
    public List<UserItem> userOrganizationClients(@RequestBody UserIdRequest userIdRequest) {
        return userItemService.getUsersByOrganization(userIdRequest.getUserId());
    }
}