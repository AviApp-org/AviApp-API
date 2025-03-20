package br.com.aviapp.api.domain.utils;

import br.com.aviapp.api.application.dto.CepResponseDTO;

public interface CepService {
    CepResponseDTO consultarCEP(String cep);

}
