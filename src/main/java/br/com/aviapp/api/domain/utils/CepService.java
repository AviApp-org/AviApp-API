package br.com.aviapp.api.domain.utils;

import br.com.aviapp.api.application.dto.CepResponseDTO;
import br.com.aviapp.api.domain.entities.AddressBO;

public interface CepService {
    CepResponseDTO consultarCEP(String cep);

}
