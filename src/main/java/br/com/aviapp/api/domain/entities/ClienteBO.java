package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
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

  public ClienteBO(Long id, String nome, String email, String cnpj, String telefone) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cnpj = cnpj;
    this.telefone = telefone;
    this.validate();
  }

  public void validate() throws IllegalArgumentException {
    if (this.nome == null) {
      throw new IllegalArgumentException("Nome não pode estar nulo");
    }

    if (this.email == null) {
      throw new IllegalArgumentException("Email não pode estar nulo");
    }

    if (this.cnpj == null) {
      throw new IllegalArgumentException("Cnpj não pode estar nulo");
    }

    if (this.telefone == null) {
      throw new IllegalArgumentException("Telefone não pode estar nulo");
    }
  }
  
  public void desativar() {
    this.status = EnumStatusCliente.INATIVO;
  }

  public void ativar() {
    this.status = EnumStatusCliente.ATIVO;
  }
}
