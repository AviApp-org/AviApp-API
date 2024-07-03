package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import br.com.aviapp.api.infra.mysql.enums.ClientStatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClienteBO {
  private Long id;
  private String name;
  private String email;
  private String cpf;
  private String phone;
  private ClientStatusType status;

  public ClienteBO(Long id, String name, String email, String cpf, String phone) throws InvalidParamError {
    ParamValidator.validate(name, email, cpf, phone);
    this.id = id;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
  }

  public void desativar() {
    this.status = ClientStatusType.INACTIVE;
  }

  public void ativar() {
    this.status = ClientStatusType.ACTIVE;
  }
}
