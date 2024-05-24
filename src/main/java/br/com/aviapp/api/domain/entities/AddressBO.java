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
public class AddressBO {
  private Long id;
  private String street;
  private String cep;
  private String neighborhood;
  private String city;
  private String state;

  public AddressBO(Long id, String street, String cep, String neighborhood, String city, String state)
      throws InvalidParamError {
    ParamValidator.validate(cep, street, neighborhood, city, state);
    this.id = id;
    this.street = street.trim();
    this.cep = cep.trim();
    this.neighborhood = neighborhood.trim();
    this.city = city.trim();
    this.state = state.trim();
  }
}
