package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.CreationValidator;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
public class CollectEggBO implements CreationValidator {

    private final Long id;
    private final AviaryBO aviary;
    private final List<EggDetailBO> eggDetails;
    private final LocalDateTime collectionDate;

    public CollectEggBO(Long id, AviaryBO aviary, List<EggDetailBO> eggDetails,
                        LocalDateTime collectionDate) throws InvalidParamError {
        ParamValidator.validate(aviary, eggDetails);

        this.id = id;
        this.aviary = aviary;
        this.eggDetails = Collections.unmodifiableList(eggDetails);
        this.collectionDate = collectionDate;
    }

    @Override
    public void validateForCreation() throws BusinessRuleException {
        for (EggDetailBO eggDetail : eggDetails) {
            if (ValidateNegative.isNegative(eggDetail.getQuantity())
            ) {
                throw new BusinessRuleException("A quantidade de ovos não pode ser negativa.");
            }
        }
        if (aviary.getBatchId().getStatus() == EnumBatchStatus.INACTIVE) {
            throw new BusinessRuleException("O lote deve estar ativo para coletar ovos.");
        }

    }
}
