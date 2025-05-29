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


    public AviaryBO(Long id, String name, Integer initialAmountOfRoosters,
                    Integer initialAmountOfChickens, Integer currentAmountOfRoosters,
                    Integer currentAmountOfChickens, BatchBO batchId) {
        ParamValidator.validate(name, initialAmountOfRoosters, initialAmountOfChickens, batchId);

        if (initialAmountOfRoosters < 0 || initialAmountOfChickens < 0) {
            throw new IllegalArgumentException("A quantidade inicial não pode ser negativa.");
        }

        // Atribuições
        this.id = id;
        this.name = name;
        this.initialAmountOfRoosters = initialAmountOfRoosters;
        this.initialAmountOfChickens = initialAmountOfChickens;
        this.currentAmountOfRoosters = currentAmountOfRoosters;
        this.currentAmountOfChickens = currentAmountOfChickens;
        this.batchId = batchId;
    }

    public void validateForCreation() throws BusinessRuleException {
        if(batchId.getStatus() == EnumBatchStatus.INACTIVE){
            throw new BusinessRuleException("O lote deve estar ativo para criar um aviário.");
        }
    }

    @Override
    public void validateDeletion() throws BusinessRuleException {
        if(batchId.getStatus() == EnumBatchStatus.ACTIVE){
            throw new BusinessRuleException("O lote deve estar inativo para excluir o aviário.");
        }
    }
}