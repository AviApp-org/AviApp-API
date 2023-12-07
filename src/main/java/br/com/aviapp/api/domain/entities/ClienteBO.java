package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
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
    ParamValidator.validate(nome, email, cnpj, telefone);
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cnpj = cnpj;
    this.telefone = telefone;
  }

  public void desativar() {
    this.status = EnumStatusCliente.INATIVO;
  }

  public void ativar() {
    this.status = EnumStatusCliente.ATIVO;
  }
}
