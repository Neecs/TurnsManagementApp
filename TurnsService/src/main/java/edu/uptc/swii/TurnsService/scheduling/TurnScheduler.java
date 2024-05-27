package edu.uptc.swii.TurnsService.scheduling;

import edu.uptc.swii.TurnsService.model.Turn;
import edu.uptc.swii.TurnsService.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TurnScheduler {

    @Autowired
    TurnService turnService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(cron = "*/30 * * * * * ")
    public void findNextTurn() {
        List<Turn> allTurns = turnService.getAllTurns();
        LocalDateTime actualTime = LocalDateTime.now();

        for (Turn turn : allTurns) {

            if (turn.getScheduledDate() != null) {


                long duration = Duration.between(LocalDateTime.now(), turn.getScheduledDate()).toMinutes();

                if ((duration > 0 && duration <= 30) && !turn.getIsEmailSended()) {
                    turnService.updateTurn(turn);
                    kafkaTemplate.send("User-id-to-be-attended", "userId", turn.getUserId());
                }
            }
        }


    }


}
