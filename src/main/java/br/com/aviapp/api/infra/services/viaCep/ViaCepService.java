package br.com.aviapp.api.infra.services.viaCep;

import br.com.aviapp.api.application.dto.CepResponseDTO;
import br.com.aviapp.api.domain.utils.CepService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService implements CepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/%s/json/";

    private final RestTemplate restTemplate;

    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CepResponseDTO consultarCEP(String cep) {
        String url = String.format(VIACEP_URL, cep);
        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        if (response == null || response.isErro()) {
            throw new RuntimeException("CEP não encontrado ou inválido");
        }

        return new CepResponseDTO(
                response.getLogradouro(),
                response.getCep(),
                response.getBairro(),
                response.getLocalidade(),
                response.getUf()
        );
    }
}