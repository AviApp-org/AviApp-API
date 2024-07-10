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
public class GranjaBO {
  private Long id;
  private ClientBO cliente;
  private AddressBO endereco;
  private LoteBO[] lote;
}
