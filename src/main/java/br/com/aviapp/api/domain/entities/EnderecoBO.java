package br.com.aviapp.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoBO {
  private Long id;
  private String logradouro;
  private String cep;
  private String bairro;
  private String cidade;
  private String estado;
}
