package br.com.aviapp.api.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

@Getter
public class EggValueBO {

    private final Long id;

    private EnumEggType eggType;

    private final LocalDateTime timestamp;

    private final BigDecimal value;

    private BatchBO batch;

    public EggValueBO(Long id, EnumEggType eggType, LocalDateTime timestamp, BigDecimal value, BatchBO batch) throws InvalidParamError {
        ParamValidator.validate(eggType,value);

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParamError("Não é permitido valores negativos para valor do ovo.");

        }

        this.id = id;
        this.eggType = eggType;
        this.timestamp = timestamp;
        this.value = value;
        this.batch = batch;
    }

    public void setEggType(EnumEggType eggType) {
        this.eggType = eggType;
    }

}
