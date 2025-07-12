package br.com.aviapp.api.application.usecases.address;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.gateways.IAddress;

import java.util.Optional;

public class GetAddressByFarmIdUseCase {

    private final IAddress repository;


    public GetAddressByFarmIdUseCase(IAddress repository) {
        this.repository = repository;
    }

    public Optional<AddressDTO> invoke(Long farmId) {
        return repository.getAddressByFarmId(farmId);
    }
}
