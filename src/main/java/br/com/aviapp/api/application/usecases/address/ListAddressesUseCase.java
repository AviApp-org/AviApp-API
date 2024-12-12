package br.com.aviapp.api.application.usecases.address;

import java.util.List;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class ListAddressesUseCase {
    private final AddressRepository repository;

    public ListAddressesUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public List<AddressBO> invoke() {
        return repository.listAllAdresses();
    }

    
}
