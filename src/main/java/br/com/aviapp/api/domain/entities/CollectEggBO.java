package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.domain.utils.ValidateNegative;
import lombok.Getter;

@Getter
public class CollectEggBO {
    
    private final Long id;
    private final CollectBO collect;
    private final EnumEggType egg;
    private final Integer quantity;

    public CollectEggBO(Long id, CollectBO collect, EnumEggType egg, Integer quantity) throws InvalidParamError {
        ParamValidator.validate(collect,egg,quantity);

        if (ValidateNegative.isNegative(quantity)){
            throw new InvalidParamError("Não é permitido valores negativos para quantidade de ovos.");
        }
        this.id = id;
        this.collect = collect;
        this.egg = egg;
        this.quantity = quantity;
    }

}
