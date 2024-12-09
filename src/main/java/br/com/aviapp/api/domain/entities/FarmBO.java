package br.com.aviapp.api.domain.entities;

import java.util.List;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FarmBO {
  private Long id;
  private ClientBO client;
  private AddressBO address;
  private List<BatchBO> batch;

  public FarmBO(Long id, ClientBO client, AddressBO address, List<BatchBO> batch) 
  throws InvalidParamError{
    ParamValidator.validate(client, address, batch);
    this.id = id;
    this.client = client;
    this.address = address;
    this.batch = List.copyOf(batch);
  }
  
}
