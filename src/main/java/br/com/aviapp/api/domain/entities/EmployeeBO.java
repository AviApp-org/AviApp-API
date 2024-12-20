package br.com.aviapp.api.domain.entities;

import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumEmployeeRole;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

public class EmployeeBO {
    private Long id;
    private String name;
    private EnumEmployeeRole role;
    private Date createdAt;
    private FarmBO farm;

    public EmployeeBO() {
    }

    public EmployeeBO(Long id, String name, EnumEmployeeRole role, Date createdAt, FarmBO farm)
            throws InvalidParamError {
        ParamValidator.validate(name, role, createdAt, farm);
        this.id = id;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
        this.farm = farm;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EnumEmployeeRole getRole() {
        return role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public FarmBO getFarm() {
        return farm;
    }

    public void setRole(EnumEmployeeRole role) {
        this.role = role;
    }
}
