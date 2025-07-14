package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

@Getter
public class AnomalyBO {
    
    private Long id;
    private String description;
    private AviaryBO aviary;

    public AnomalyBO(Long id, String description, AviaryBO aviary) throws InvalidParamError{
        ParamValidator.validate(description);
        this.id = id;
        this.description = description;
        this.aviary = aviary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
