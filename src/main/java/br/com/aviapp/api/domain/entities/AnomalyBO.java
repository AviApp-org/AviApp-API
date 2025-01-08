package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

@Getter
public class AnomalyBO {
    
    private Long id;
    private String description;

    public AnomalyBO(Long id, String description) throws InvalidParamError{
        ParamValidator.validate(description);
        this.id = id;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
