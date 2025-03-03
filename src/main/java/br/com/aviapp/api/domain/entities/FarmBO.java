package br.com.aviapp.api.domain.entities;

import java.util.List;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmBO {
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

}
