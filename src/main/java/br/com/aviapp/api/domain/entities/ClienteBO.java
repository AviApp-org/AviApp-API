package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.errors.InvalidParamError;
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
  private String nome;
  private String email;
  private String cnpj;
  private String telefone;
  private EnumStatusCliente status;

  public ClienteBO(Long id, String nome, String email, String cnpj, String telefone) throws InvalidParamError {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cnpj = cnpj;
    this.telefone = telefone;
    this.validate();
  }

  public void validate() throws InvalidParamError {
    if (this.nome == null) {
      throw new InvalidParamError("nome");
    }

    if (this.email == null) {
      throw new InvalidParamError("email");
    }

    if (this.cnpj == null) {
      throw new InvalidParamError("cnpj");
    }

    if (this.telefone == null) {
      throw new InvalidParamError("telefone");
    }
  }
  
  public void desativar() {
    this.status = EnumStatusCliente.INATIVO;
  }

  public void ativar() {
    this.status = EnumStatusCliente.ATIVO;
  }
}
