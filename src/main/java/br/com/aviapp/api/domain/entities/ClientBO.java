package br.com.aviapp.api.domain.entities;

import java.time.LocalDate;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import br.com.aviapp.api.domain.utils.ParamValidator;
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
    ParamValidator.validate(name, email, cpf, phone);
    this.id = id;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
    this.birthDate = birthDate;
    this.status = ClientStatusEnum.ACTIVE;
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
}
