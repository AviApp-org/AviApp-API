package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
public class CollectEggBO {

    private final Long id;
    private final AviaryBO aviary;
    private final List<EggDetailBO> eggDetails;
    private final LocalDateTime collectionDate;

    public CollectEggBO(Long id, AviaryBO aviary, List<EggDetailBO> eggDetails,
                        LocalDateTime collectionDate) throws InvalidParamError {
        ParamValidator.validate(aviary, eggDetails, collectionDate);

        if (eggDetails == null || eggDetails.isEmpty()) {
            throw new InvalidParamError("É necessário informar pelo menos um tipo de ovo coletado.");
        }

        this.id = id;
        this.aviary = aviary;
        this.eggDetails = Collections.unmodifiableList(eggDetails);
        this.collectionDate = collectionDate;
    }

    public Integer getTotalEggs() {
        return eggDetails.stream()
                .mapToInt(EggDetailBO::getQuantity)
                .sum();
    }

}
