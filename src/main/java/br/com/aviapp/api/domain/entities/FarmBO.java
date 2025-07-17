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
    private AddressBO address;
    private List<EmployeeBO> employees;

    public FarmBO(Long id, String name, ClientBO client, AddressBO address, List<EmployeeBO> employees)
            throws InvalidParamError {
        ParamValidator.validate(client, address, employees);

        this.id = id;
        this.name = name;
        this.client = client;
        this.address = address;
        this.employees = List.copyOf(employees);
    }


    @Override
    public String toString() {
        return "FarmBO [id=" + id + ", client=" + client + ", address=" + address + "]";
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
