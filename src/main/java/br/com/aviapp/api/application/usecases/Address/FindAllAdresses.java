package br.com.aviapp.api.application.usecases.Address;

import java.util.List;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class FindAllAdresses {
    private final AddressRepository repository;

    public FindAllAdresses(AddressRepository repository) {
        this.repository = repository;
    }

    public List<AddressBO> listarEnderecos() {
        return repository.listAllAdresses();
    }

}
