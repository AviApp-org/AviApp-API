package br.com.aviapp.api.domain.entities;

import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumEmployeeRole;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeBO {
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private EnumEmployeeRole role;
    private Date createdAt;
    private FarmBO farm;

    public EmployeeBO() {
    }

    public EmployeeBO(Long id, String name,String cpf, String phone, EnumEmployeeRole role, Date createdAt, FarmBO farm)
            throws InvalidParamError {
        ParamValidator.validate(name, role, createdAt, farm);
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
        this.farm = farm;
    }

}
