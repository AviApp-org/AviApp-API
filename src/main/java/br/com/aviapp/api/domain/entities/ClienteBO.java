package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
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
    this.status = EnumStatusCliente.ATIVO;
    validate();
  }

  public void validate() {
    if (this.nome == null) {
      throw new RuntimeException("Nome não pode estar nulo");
    }

    if (this.email == null) {
      throw new RuntimeException("Email não pode estar nulo");
    }

    if (this.cnpj == null) {
      throw new RuntimeException("Cnpj não pode estar nulo");
    }

    if (this.telefone == null) {
      throw new RuntimeException("Telefone não pode estar nulo");
    }
  }
  
}
