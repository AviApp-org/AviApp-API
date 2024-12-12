package br.com.aviapp.api.application.usecases.address;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class CreateAddressUseCase {
    
    private final AddressRepository repository;

    public CreateAddressUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressBO invoke (AddressBO address){
        return repository.createAdrress(address);
    }
    
}
