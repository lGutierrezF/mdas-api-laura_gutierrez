package trainers.trainer.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import trainers.trainer.application.CreateTrainer;
import trainers.trainer.domain.exceptions.TrainerAlreadyCreatedException;

@RestController
public class CreateTrainerWithHttp {
    @PostMapping("create-trainer/{ID}")
    public static void CreateTrainer(@PathVariable String ID) {
        var trainerRepoository = new InMemoryTrainerRepository();
        var createTrainer = new CreateTrainer(trainerRepoository);

        blankIdGuard(ID);
        try {
            createTrainer.execute(ID);
        } catch (TrainerAlreadyCreatedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"TrainerAlreadyCreatedException");
        }
    }

    private static void blankIdGuard(String ID) {
        if (ID.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blankID");
        }
    }
}


