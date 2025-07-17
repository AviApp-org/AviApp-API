package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.CreationValidator;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CollectChickenBO implements CreationValidator {

    private final Long id;
    private final AviaryBO aviary;
    private final Integer deadRoosters;
    private final Integer deadChickens;
    private final String observation;
    private final LocalDateTime collectionDate;

    public CollectChickenBO(Long id, AviaryBO aviary, Integer deadRoosters, Integer deadChickens,
                            String observation, LocalDateTime collectionDate) throws InvalidParamError {

        ParamValidator.validate(aviary, deadChickens, deadRoosters);

        this.id = id;
        this.aviary = aviary;
        this.deadRoosters = deadRoosters;
        this.deadChickens = deadChickens;
        this.observation = observation;
        this.collectionDate = collectionDate;
    }

    @Override
    public void validateForCreation() throws BusinessRuleException {
        if (ValidateNegative.isNegative(deadChickens) || ValidateNegative.isNegative(deadRoosters)) {
            throw new InvalidParamError("Não é permitido valores negativos para aves mortas.");
        }

        if (aviary.getBatchId().getStatus() == EnumBatchStatus.INACTIVE) {
            throw new BusinessRuleException("O lote deve estar ativo para enviar relatório de aves.");
        }
    }
}
