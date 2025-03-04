package br.com.aviapp.api.domain.services;

import br.com.aviapp.api.application.dto.CepResponseDTO;
import br.com.aviapp.api.domain.entities.AddressBO;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.CepService;

public class AddressService {
    private final CepService cepService;

    public AddressService(CepService cepService) {
        this.cepService = cepService;
    }

    public CepResponseDTO consultarEnderecoPorCEP(String cep) throws InvalidParamError {
        CepResponseDTO endereco = cepService.consultarCEP(cep);
        // Valida e retorna o endereço
        return new CepResponseDTO(
                endereco.street(),
                endereco.cep(),
                endereco.neighborhood(),
                endereco.city(),
                endereco.state()
        );
    }
}
