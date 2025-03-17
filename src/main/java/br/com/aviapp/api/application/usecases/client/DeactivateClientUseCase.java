package br.com.aviapp.api.application.usecases.client;

import java.util.Optional;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.ClientRepository;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class DeactivateClientUseCase {

     private final ClientRepository repository;
    private final ClientMapperBO mapperBO;
    
    public DeactivateClientUseCase(ClientRepository repository, ClientMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public void invoke(Long clientId) {
       Optional<ClientDTO> clientDTO = repository.findClient(clientId);
       ClientBO clientBO = mapperBO.toBO(clientDTO.get());

       clientBO.desativar();
       repository.save(mapperBO.toDTO(clientBO));
    }
}
