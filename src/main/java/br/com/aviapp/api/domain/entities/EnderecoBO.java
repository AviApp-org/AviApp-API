package br.com.aviapp.api.domain.entities;

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
public class EnderecoBO {
  private Long id;
  private String logradouro;
  private String cep;
  private String bairro;
  private String cidade;
  private String estado;

  public EnderecoBO(Long id, String logradouro, String cep, String bairro, String cidade, String estado) throws InvalidParamError {
    ParamValidator.validate(cep, logradouro, bairro, cidade, estado);
    this.id = id;
    this.logradouro = logradouro.trim();
    this.cep = cep.trim();
    this.bairro = bairro.trim();
    this.cidade = cidade.trim();
    this.estado = estado.trim();
  }
}
