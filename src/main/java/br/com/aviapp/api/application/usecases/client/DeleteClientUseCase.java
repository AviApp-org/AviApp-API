package br.com.aviapp.api.application.usecases.client;

import java.util.Optional;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.ClientRepository;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class DeleteClientUseCase {

    private final ClientRepository repository;
    private final ClientMapperBO mapper;

    public DeleteClientUseCase(ClientRepository repository, ClientMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void invoke(Long clientID) {
        // Verify if client exists and convert to BO for any business rules
        Optional<ClientDTO> clientDTO = repository.findClient(clientID);
        if (clientDTO.isPresent()) {
            ClientBO clientBO = mapper.toBO(clientDTO.get());
            repository.deleteClient(clientID);
        }
    }
}
