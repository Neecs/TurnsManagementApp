package edu.uptc.swii.TurnsManagementAppusersmanagement.controller;

import edu.uptc.swii.TurnsManagementAppusersmanagement.entity.UserItem;
import edu.uptc.swii.TurnsManagementAppusersmanagement.model.UserDTO;
import edu.uptc.swii.TurnsManagementAppusersmanagement.model.UserIdRequest;
import edu.uptc.swii.TurnsManagementAppusersmanagement.service.IKeycloakService;
import edu.uptc.swii.TurnsManagementAppusersmanagement.service.UserItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Get all users", description = "Retrieves all users from the Keycloak service", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping("/allUsers")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @Operation(summary = "Create user", description = "Creates a new user in the Keycloak service", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User Created", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        String response = keycloakService.createUser(userDTO);
        return ResponseEntity.created(new URI("/create")).body(response);
    }

    @Operation(summary = "Store user", description = "Stores a new user in the local database", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Stored Successfully", content = {@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping("/store")
    public String storeNewUser(@RequestBody UserItem userItem) {
        userItemService.save(userItem);
        return "User stored successfully";
    }

    @Operation(summary = "Check user existence", description = "Checks if a user exists in the local database", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Exists", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "404", description = "User Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping("/checkExistence")
    public boolean checkUserStorage(@RequestBody UserIdRequest userIdRequest) {
        String userId = userIdRequest.getUserId();
        return userItemService.checkUserExistenceInDb(userId);
    }

    @Operation(summary = "Get users by role", description = "Retrieves users with a specific role from the Keycloak service", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping("/getUsersRole")
    public ResponseEntity<?> findUsers() {
        return ResponseEntity.ok(keycloakService.findUsers());
    }

    @Operation(summary = "Get organization admins", description = "Retrieves admins of a specific organization", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping("/getUserOrganizationDependents")
    public List<UserItem> userOrganizationAdmins(@RequestBody UserIdRequest userIdRequest) {
        return userItemService.getAdminsByOrganization(userIdRequest.getUserId());
    }

    @Operation(summary = "Get organization clients", description = "Retrieves clients of a specific organization", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping("/getUserOrganizationClients")
    public List<UserItem> userOrganizationClients(@RequestBody UserIdRequest userIdRequest) {
        return userItemService.getUsersByOrganization(userIdRequest.getUserId());
    }
