package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

public class AddressBO {
  private Long id;
  private String street;
  private String number;
  private String cep;
  private String neighborhood;
  private String city;
  private String state;

  public AddressBO() {
  }

  public AddressBO(Long id, String street, String number, String cep, String neighborhood, String city, String state)
      throws InvalidParamError {
    ParamValidator.validate(cep, street, number, neighborhood, city, state);
    this.id = id;
    this.street = street.trim();
    this.number = number.trim();
    this.cep = cep.trim();
    this.neighborhood = neighborhood.trim();
    this.city = city.trim();
    this.state = state.trim();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "AddressBO [id=" + id + ", street=" + street + ", number=" + number + ", cep=" + cep + ", neighborhood="
        + neighborhood + ", city=" + city + ", state=" + state + "]";
  }
}
