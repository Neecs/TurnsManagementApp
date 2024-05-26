package edu.uptc.swii.TurnsService.controller;

import edu.uptc.swii.TurnsService.model.Turn;
import edu.uptc.swii.TurnsService.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/turns")
public class TurnsController {
    @Autowired
    TurnService turnService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('turnsmanagementapp_client_user_role') OR hasRole('turnsmanagementapp_client_admin_role')")
    public void createTurn(@RequestBody Turn turn) {
        turnService.saveTurn(turn);
    }

    @GetMapping("/getUserTurns/{userId}")
    @PreAuthorize("hasRole('turnsmanagementapp_client_user_role')")
    public List<Turn> getTurnsByUserId(@PathVariable String userId) {
        return turnService.getTurnsByUserId(userId);
    }

    @GetMapping("/getAdminTurns/{adminId}")
    @PreAuthorize("hasRole('turnsmanagementapp_client_admin_role')")
    public List<Turn> getTurnsByDependentId(@PathVariable String adminId) {
        return turnService.getTurnsByDependentId(adminId);
    }

    @PostMapping("/update")
    public void updateTurnAvailability(@RequestBody Turn turnRequest){
               turnService.updateTurn(turnRequest);
    }
}
