package br.com.aviapp.api.application.usecases.address;

import java.util.List;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.gateways.AddressRepository;

public class ListAddressesUseCase {
    private final AddressRepository repository;

    public ListAddressesUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public List<AddressDTO> invoke() {
        return repository.listAllAddresses();
    }

    
}
