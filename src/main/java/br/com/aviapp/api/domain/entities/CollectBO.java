package br.com.aviapp.api.domain.entities;

import java.time.LocalDateTime;

public class CollectBO {
    
    private Long id;
    private AviaryBO aviary;
    private EmployeeBO employee;
    private LocalDateTime timestamp;
    
    public CollectBO(Long id, AviaryBO aviary, EmployeeBO employee, LocalDateTime timestamp) {
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
