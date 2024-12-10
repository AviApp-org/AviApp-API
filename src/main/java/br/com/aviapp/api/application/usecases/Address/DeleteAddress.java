package br.com.aviapp.api.application.usecases.Address;

import br.com.aviapp.api.application.gateways.AddressRepository;

public class DeleteAddress {

    private final AddressRepository repository;

    public DeleteAddress(AddressRepository repository) {
        this.repository = repository;
    }

    public void deletarAddressBO (Long addressID){
        repository.deleteAddress(addressID);
    }
}
