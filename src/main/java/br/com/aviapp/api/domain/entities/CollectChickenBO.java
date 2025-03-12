package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumChickenDeathCause;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

public class CollectChickenBO {

    private final Long id;
    private final CollectBO collect;
    private final Integer deadRoosters;
    private final Integer deadChickens;
    private final Integer totalDeath;
    private final EnumChickenDeathCause deathCause;
    private final String observation;

    public CollectChickenBO(Long id, CollectBO collect, Integer deadRoosters, Integer deadChickens, Integer totalDeath,
            EnumChickenDeathCause deathCause, String observation) throws InvalidParamError {
        ParamValidator.validate(collect,deadChickens,deadRoosters,totalDeath,deathCause);
        this.id = id;
        this.collect = collect;
        this.deadRoosters = deadRoosters;
        this.deadChickens = deadChickens;
        this.totalDeath = totalDeath;
        this.deathCause = deathCause;
        this.observation = observation;
    }

    public Long getId() {
        return id;
    }

    public CollectBO getCollect() {
        return collect;
    }

    public Integer getDeadRoosters() {
        return deadRoosters;
    }

    public Integer getDeadChickens() {
        return deadChickens;
    }

    public Integer getTotalDeath() {
        return totalDeath;
    }

    public EnumChickenDeathCause getDeathCause() {
        return deathCause;
    }

    public String getObservation() {
        return observation;
    }
}
