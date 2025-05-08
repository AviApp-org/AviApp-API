package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CollectChickenBO {

    private final Long id;
    private final AviaryBO aviary;
    private final Integer deadRoosters;
    private final Integer deadChickens;
    private final String observation;
    private final LocalDateTime collectionDate;

    public CollectChickenBO(Long id, AviaryBO aviary, Integer deadRoosters, Integer deadChickens,
                            String observation, LocalDateTime collectionDate) throws InvalidParamError {

        ParamValidator.validate(aviary, deadChickens, deadRoosters);

        if (ValidateNegative.isNegative(deadChickens) || ValidateNegative.isNegative(deadRoosters)) {
            throw new InvalidParamError("Não é permitido valores negativos para aves mortas.");
        }
        this.id = id;
        this.aviary = aviary;
        this.deadRoosters = deadRoosters;
        this.deadChickens = deadChickens;
        this.observation = observation;
        this.collectionDate = collectionDate;
    }

    public Integer getTotalDeadBirds() {
        return deadRoosters + deadChickens;
    }

}
