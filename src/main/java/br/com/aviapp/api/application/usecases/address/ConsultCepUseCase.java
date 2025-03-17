package br.com.aviapp.api.application.usecases.address;

import br.com.aviapp.api.application.dto.CepResponseDTO;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.services.AddressService;

public class ConsultCepUseCase {
    private final AddressService addressService;

    public ConsultCepUseCase(AddressService addressService) {
        this.addressService = addressService;
    }

    public CepResponseDTO invoke(String cep) throws InvalidParamError {
        return addressService.consultarEnderecoPorCEP(cep);
    }

}
