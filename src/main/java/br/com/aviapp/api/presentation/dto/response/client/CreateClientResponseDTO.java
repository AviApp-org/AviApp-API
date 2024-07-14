package br.com.aviapp.api.presentation.dto.response.client;

import java.time.LocalDate;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateClientResponseDTO {
  private Long id;
  private String name;
  private String email;
  private String cpf;
  private String phone;
  private LocalDate birthDate;
  private ClientStatusEnum status;
}
