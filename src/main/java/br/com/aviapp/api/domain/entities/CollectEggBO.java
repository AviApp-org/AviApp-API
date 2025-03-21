package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

public class CollectEggBO {
    
    private Long id;
    private CollectBO collect;
    private EnumEggType egg;
    private Integer quantity;

    public CollectEggBO(Long id, CollectBO collect, EnumEggType egg, Integer quantity) throws InvalidParamError {
        ParamValidator.validate(collect,egg,quantity);
        this.id = id;
        this.collect = collect;
        this.egg = egg;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public CollectBO getCollect() {
        return collect;
    }

    public EnumEggType getEgg() {
        return egg;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
}
