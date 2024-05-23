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

    public List<Turn> getTurnsByDependentId(String dependentId) {
        return turnRepo.getTurnsByDependentId(dependentId);
    }
}
