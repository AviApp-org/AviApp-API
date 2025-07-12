package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressBO {
    private Long id;
    private String street;
    private String number;
    private String cep;
    private String neighborhood;
    private String city;
    private String state;
    private Long farmId;

    public AddressBO() {
    }

    public AddressBO(Long id, String street, String number, String cep, String neighborhood, String city, String state, Long farmId)
            throws InvalidParamError {
        ParamValidator.validate(cep, street, number, neighborhood, city, state);
        this.id = id;
        this.street = street.trim();
        this.number = number.trim();
        this.cep = cep.trim();
        this.neighborhood = neighborhood.trim();
        this.city = city.trim();
        this.state = state.trim();
        this.farmId = farmId;
    }

    @Override
    public String toString() {
        return "AddressBO [id=" + id + ", street=" + street + ", number=" + number + ", cep=" + cep + ", neighborhood="
                + neighborhood + ", city=" + city + ", state=" + state + "]";
    }
}
