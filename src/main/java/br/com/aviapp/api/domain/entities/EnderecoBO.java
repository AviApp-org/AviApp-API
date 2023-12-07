package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EnderecoBO {
  private Long id;
  private String logradouro;
  private String cep;
  private String bairro;
  private String cidade;
  private String estado;

  public EnderecoBO(Long id, String logradouro, String cep, String bairro, String cidade, String estado) throws InvalidParamError {
    this.id = id;
    this.logradouro = logradouro.trim();
    this.cep = cep.trim();
    this.bairro = bairro.trim();
    this.cidade = cidade.trim();
    this.estado = estado.trim();
    this.validate();
  }

  private void validate() {
    if (this.cep == null || this.cep.isBlank()) {
      throw new InvalidParamError("cep");
    }

    if (this.logradouro == null || this.logradouro.isBlank()) {
      throw new InvalidParamError("logradouro");
    }

    if (this.bairro == null || this.bairro.isBlank()) {
      throw new InvalidParamError("bairro");
    }

    if (this.cidade == null || this.cidade.isBlank()) {
      throw new InvalidParamError("cidade");
    }

    if (this.estado == null || this.estado.isBlank()) {
      throw new InvalidParamError("estado");
    }
  }
}
