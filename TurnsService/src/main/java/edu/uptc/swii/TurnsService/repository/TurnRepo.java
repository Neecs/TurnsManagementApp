package edu.uptc.swii.TurnsService.repository;

import edu.uptc.swii.TurnsService.model.Turn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface TurnRepo extends MongoRepository<Turn, String>{
    @Query("{userId: ?0}")
    List<Turn> getTurnsByUserId(String userId);

    @Query("{dependentId: ?0}")
    List<Turn> getTurnsByDependentId(String dependentId);

    @Query("{_id: ?0}")
    Turn findTurnById(String turnId);
}
