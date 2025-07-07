package br.com.aviapp.api.application.usecases.address;

import java.util.List;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.gateways.IAddress;

public class ListAddressesUseCase {

    private final IAddress repository;

    public ListAddressesUseCase(IAddress repository) {
        this.repository = repository;
    }

    public List<AddressDTO> invoke() {
        return repository.listAllAddresses();
    }

    
}
