package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumChickenDeathCause;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;

@Getter
public class CollectChickenBO {

    private final Long id;
    private final CollectBO collect;
    private final Integer deadRoosters;
    private final Integer deadChickens;
    private final EnumChickenDeathCause deathCause;
    private final String observation;

    public CollectChickenBO(Long id, CollectBO collect, Integer deadRoosters, Integer deadChickens,
            EnumChickenDeathCause deathCause, String observation) throws InvalidParamError {
        ParamValidator.validate(collect,deadChickens,deadRoosters,deathCause);

        if(ValidateNegative.isNegative(deadChickens) || ValidateNegative.isNegative(deadRoosters)) {
            throw new InvalidParamError("Não é permitido valores negativos para aves mortas.");
        }
        this.id = id;
        this.collect = collect;
        this.deadRoosters = deadRoosters;
        this.deadChickens = deadChickens;
        this.deathCause = deathCause;
        this.observation = observation;
    }

    public Integer getTotalDeadBirds() {
        return deadRoosters + deadChickens;
    }

}
