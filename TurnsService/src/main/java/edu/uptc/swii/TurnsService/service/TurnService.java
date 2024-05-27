package edu.uptc.swii.TurnsService.service;

import edu.uptc.swii.TurnsService.model.Turn;
import edu.uptc.swii.TurnsService.repository.TurnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnService {
    @Autowired
    TurnRepo turnRepo;

    public void saveTurn(Turn turn) {
        turnRepo.save(turn);
    }

    public List<Turn> getTurnsByUserId(String userId) {
        return turnRepo.getTurnsByUserId(userId);
    }

    public List<Turn> getAllTurns(){
        return turnRepo.findAll();
    }

    public List<Turn> getTurnsByDependentId(String dependentId) {
        return turnRepo.getTurnsByDependentId(dependentId);
    }

    public void updateTurn(Turn turnRequest){
        Turn existingTurn = turnRepo.findTurnById(turnRequest.getId());
        existingTurn.setIsEmailSended(true);
        turnRepo.save(existingTurn);
    }

    public void deleteTurn(Turn turnRequest){
        Turn turnToDelete = turnRepo.findTurnById(turnRequest.getId());
        turnRepo.delete(turnToDelete);
    }
}
