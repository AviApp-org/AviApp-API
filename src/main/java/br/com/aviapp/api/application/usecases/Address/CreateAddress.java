package br.com.aviapp.api.application.usecases.Address;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class CreateAddress {
    
    private final AddressRepository repository;

    public CreateAddress(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressBO cadastrarAddressBO (AddressBO address){
        return repository.createAdrress(address);
    }
    
}
