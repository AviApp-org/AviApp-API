package br.com.aviapp.api.domain.dto;

import br.com.aviapp.api.infra.mysql.enums.ClientStatusType;
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
public class ClientDTO {
  private Long id;
  private String name;
  private String email;
  private String cpf;
  private String telefone;
  private ClientStatusType status;

  public ClientDTO(Long id, String name, String email, String cpf, String telefone) {
    this.id = id != null ? id : null;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.telefone = telefone;
    this.status = ClientStatusType.ACTIVE;
  }
}
