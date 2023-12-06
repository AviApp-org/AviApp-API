package br.com.aviapp.api.domain.dto;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
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
public class ClienteDTO {
  private Long id;
  private String nome;
  private String email;
  private String cnpj;
  private String telefone;
  private EnumStatusCliente status;

  public ClienteDTO(Long id, String nome, String email, String cnpj, String telefone) {
    this.id = id != null ? id : null;
    this.nome = nome;
    this.email = email;
    this.cnpj = cnpj;
    this.telefone = telefone;
    this.status = EnumStatusCliente.ATIVO;
  }
}
