package br.com.aviapp.api.application.usecases.client;

import java.util.Optional;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.ClientRepository;
import br.com.aviapp.api.application.mappers.ClientMapperBO;

public class FindClientByIdUseCase {

    private final ClientRepository repository;
    private final ClientMapperBO mapper;

    public FindClientByIdUseCase(ClientRepository repository, ClientMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<ClientDTO> invoke(Long clientID) {
        return repository.findClient(clientID)
                .map(mapper::toBO) // Convert to domain for any business rules
                .map(mapper::toDTO);
    }
}
