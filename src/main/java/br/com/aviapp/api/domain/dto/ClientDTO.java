package br.com.aviapp.api.domain.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import jakarta.validation.constraints.Email;
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

  @Email
  private String email;

  @CPF
  private String cpf;

  private String phone;

  private LocalDate birthDate;

  private ClientStatusEnum status = ClientStatusEnum.ACTIVE;

  public ClientDTO(Long id, String name, String email, String cpf, String phone) {
    this.id = id != null ? id : null;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
    this.status = ClientStatusEnum.ACTIVE;
  }
}
