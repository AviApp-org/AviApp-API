package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CollectBO {
    
    private final Long id;
    private final AviaryBO aviary;
    private final EmployeeBO employee;
    private final LocalDateTime timestamp;
    
    public CollectBO(Long id, AviaryBO aviary, EmployeeBO employee, LocalDateTime timestamp) throws InvalidParamError {
        ParamValidator.validate(aviary,employee);
        this.id = id;
        this.aviary = aviary;
        this.employee = employee;
        this.timestamp = timestamp;
    }

    
}
