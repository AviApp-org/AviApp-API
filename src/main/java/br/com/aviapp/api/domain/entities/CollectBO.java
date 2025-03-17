package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

import java.time.LocalDateTime;

public class CollectBO {
    
    private Long id;
    private AviaryBO aviary;
    private EmployeeBO employee;
    private LocalDateTime timestamp;
    
    public CollectBO(Long id, AviaryBO aviary, EmployeeBO employee, LocalDateTime timestamp) throws InvalidParamError {
        ParamValidator.validate(aviary,employee);
        this.id = id;
        this.aviary = aviary;
        this.employee = employee;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public AviaryBO getAviary() {
        return aviary;
    }

    public EmployeeBO getEmployee() {
        return employee;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    
}
