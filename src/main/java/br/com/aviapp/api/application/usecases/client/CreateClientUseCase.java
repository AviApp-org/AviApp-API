package br.com.aviapp.api.application.usecases.client;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.IClient;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class CreateClientUseCase {

    private final IClient repository;
    private final ClientMapperBO mapper;

    public CreateClientUseCase(IClient repository, ClientMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClientDTO invoke(ClientDTO client) {
         // Convert DTO to domain object for business rules
        ClientBO clientBO = mapper.toBO(client);

        // Apply domain business rules here
        validateClient(clientBO);

        // Convert back to DTO for repository
        ClientDTO dtoToSave = mapper.toDTO(clientBO);

        // Save and return
        return repository.createClient(dtoToSave);
    }

        private void validateClient(ClientBO client) {
        // Domain valida
    }
}
