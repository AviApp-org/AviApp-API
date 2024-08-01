package br.com.aviapp.api.presentation.dto.request.client;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
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
public class CreateClientRequestDTO {
  private Long id;
  private String name;

  @Email
  private String email;

  @CPF
  private String cpf;

  private String phone;

  @Past
  private LocalDate birthDate;

  private ClientStatusEnum status = ClientStatusEnum.ACTIVE;

  public CreateClientRequestDTO(Long id, String name, String email, String cpf, String phone, LocalDate birthDate) {
    this.id = id != null ? id : null;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
    this.birthDate = birthDate;
    this.status = ClientStatusEnum.ACTIVE;
  }
}
