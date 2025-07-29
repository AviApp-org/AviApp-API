package br.com.aviapp.api.domain.entities;

import java.util.List;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.CreationValidator;
import br.com.aviapp.api.domain.utils.DeletableEntity;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmBO implements CreationValidator, DeletableEntity {
    private Long id;
    private String name;
    private ClientBO client;
    private List<EmployeeBO> employees;
    private String street;
    private String number;
    private String cep;
    private String neighborhood;
    private String city;
    private String state;

    public FarmBO(Long id, String name, ClientBO client, List<EmployeeBO> employees, String street, String number, String cep, String neighborhood, String city, String state )
            throws InvalidParamError {
        ParamValidator.validate(client, employees);

        this.id = id;
        this.name = name;
        this.client = client;
        this.employees = List.copyOf(employees);
        this.street = street;
        this.number = number;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }


    @Override
    public String toString() {
        return "FarmBO [id=" + id + ", client=" + client + ",]";
    }

    @Override
    public void validateForCreation() throws BusinessRuleException {
        if (client.getStatus() == EnumStatusCliente.INACTIVE) {
            throw new BusinessRuleException("O cliente deve estar ativo para criar uma granja.");
        }
    }

    @Override
    public void validateDeletion() throws BusinessRuleException {
        if (client.getStatus() == EnumStatusCliente.ACTIVE) {
            throw new BusinessRuleException("O cliente deve estar inativo para excluir a granja.");
        }
    }

}
