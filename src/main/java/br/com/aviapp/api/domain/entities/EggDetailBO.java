package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class EggDetailBO {

    private EnumEggType type;
    private Integer quantity;

    public EggDetailBO(EnumEggType type, Integer quantity) throws InvalidParamError {
        ParamValidator.validate(type, quantity);

        if (ValidateNegative.isNegative(quantity)) {
            throw new InvalidParamError("Não é permitido valores negativos para quantidade de ovos.");
        }

        this.type = type;
        this.quantity = quantity;
    }
}
