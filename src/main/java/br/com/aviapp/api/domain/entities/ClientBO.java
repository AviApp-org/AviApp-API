package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ClientBO {
  private Long id;

  @NotBlank(message = "Nome é obrigatório")
  private String name;

  @NotBlank(message = "Email é obrigatório")
  @Email(message = "Email inválido")
  private String email;

  @NotBlank(message = "CNPJ é obrigatório")
  @Pattern(regexp = "\\d{14}", message = "CNPJ inválido")
  private String cnpj;

  @NotBlank(message = "Telefone é obrigatório")
  private String phone;
  
  private EnumStatusCliente status;

  public ClientBO() {
  }

  public ClientBO(Long id, String name, String email, String cnpj, String phone, EnumStatusCliente status)
      throws InvalidParamError {
    ParamValidator.validate(name, email, cnpj, phone, status);
    this.id = id;
    this.name = name;
    this.email = email;
    this.cnpj = cnpj;
    this.phone = phone;
    this.status = status;
  }

  public void desativar() {
    this.status = EnumStatusCliente.INACTIVE;
  }

  public void ativar() {
    this.status = EnumStatusCliente.ACTIVE;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

    @Override
  public String toString() {
    return "ClientBO [id=" + id + ", name=" + name + ", email=" + email + ", cnpj=" + cnpj + ", phone=" + phone
        + ", status=" + status + "]";
  }

}
