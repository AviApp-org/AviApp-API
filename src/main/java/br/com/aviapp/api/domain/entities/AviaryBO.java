package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.utils.DeletableEntity;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

@Getter
public class AviaryBO implements DeletableEntity {

    private final Long id;
    private String name;
    private final Integer initialAmountOfRoosters;
    private final Integer initialAmountOfChickens;
    private final Integer currentAmountOfRoosters;
    private final Integer currentAmountOfChickens;
    private final BatchBO batchId;

    public AviaryBO(Long id, String name, Integer initialAmountOfRoosters, Integer initialAmountOfChickens, Integer currentAmountOfRoosters, Integer currentAmountOfChickens, BatchBO batchId) throws IllegalArgumentException {

        ParamValidator.validate(name, initialAmountOfRoosters, initialAmountOfChickens, batchId);

        if (initialAmountOfRoosters < 0 || initialAmountOfChickens < 0) {
            throw new IllegalArgumentException("A quantidade inicial de galinhas e galos não pode ser negativa.");
        }

        if(batchId.getStatus() == EnumBatchStatus.INACTIVE){
            throw new IllegalArgumentException("O lote deve estar ativo para criar um aviário.");
        }

        this.id = id;
        this.name = name;
        this.initialAmountOfRoosters = initialAmountOfRoosters;
        this.initialAmountOfChickens = initialAmountOfChickens;
        this.currentAmountOfRoosters = currentAmountOfRoosters;
        this.currentAmountOfChickens = currentAmountOfChickens;
        this.batchId = batchId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitialAmountOfBirds() {
        return initialAmountOfRoosters + initialAmountOfChickens;
    }

    @Override
    public void validateDeletion() throws BusinessRuleException {

    }
}