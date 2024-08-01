package br.com.aviapp.api.domain.entities;

import java.time.LocalDate;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientBO {
  private Long id;
  private String name;
  private String email;
  private String cpf;
  private String phone;
  private LocalDate birthDate;
  private ClientStatusEnum status;

  public ClientBO(Long id, String name, String email, String cpf, String phone, LocalDate birthDate) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
    this.birthDate = birthDate;
    this.status = ClientStatusEnum.ACTIVE;

    this.validate();
  }

  public void deactivate() {
    this.status = ClientStatusEnum.INACTIVE;
  }

  public void activate() {
    this.status = ClientStatusEnum.ACTIVE;
  }

  public boolean isCpfValid() {
    return true;
  }

  public boolean isBirthDateValid() {
    boolean isFutureDate = this.birthDate.isAfter(LocalDate.now());
    boolean isAdult = this.birthDate.isBefore(LocalDate.now().minusYears(18));
    if (isFutureDate) {
      throw new RuntimeException("Data de nascimento não pode ser futura");
    }

    if (!isAdult) {
      throw new RuntimeException("Cliente não pode ser menor de 18 anos");
    }

    return true;
  }

  private void validate() {

    if (this.getName().isEmpty()) {
      throw new RuntimeException("Nome cannot be empty");
    }

    if (this.getEmail().isEmpty()) {
      throw new RuntimeException("Email cannot be empty");
    }

    if (this.getCpf().isEmpty()) {
      throw new RuntimeException("CPF cannot be empty");
    }

    if (this.getPhone().isEmpty()) {
      throw new RuntimeException("Phone cannot be empty");
    }

    this.isCpfValid();
    this.isBirthDateValid();
  }
}
