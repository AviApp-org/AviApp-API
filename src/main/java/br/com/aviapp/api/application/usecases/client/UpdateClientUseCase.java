package br.com.aviapp.api.application.usecases.client;

import java.util.Optional;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.ClientRepository;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class UpdateClientUseCase {

    private final ClientRepository repository;
    private final ClientMapperBO mapper;

    public UpdateClientUseCase(ClientRepository repository, ClientMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<ClientDTO> invoke(Long clientId, ClientDTO clientDTO) {
        return repository.findClient(clientId)
                .map(existingClient -> {
                    // Convert to domain objects
                    ClientBO existingBO = mapper.toBO(existingClient);
                    ClientBO updateBO = mapper.toBO(clientDTO);

                    // Apply domain validations and update rules
                    validateUpdate(existingBO, updateBO);

                    // Convert back to DTO for persistence
                    ClientDTO validatedDTO = mapper.toDTO(updateBO);

                    return repository.updateClient(clientId, validatedDTO);
                })
                .flatMap(dto -> dto);
    }

    private void validateUpdate(ClientBO existing, ClientBO update) {
        // Domain validation rules for updates
    }
}
