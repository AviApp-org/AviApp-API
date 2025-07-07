package br.com.aviapp.api.application.usecases.client;

import java.util.Optional;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.IClient;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class UpdateClientUseCase {

    private final IClient repository;
    private final ClientMapperBO mapper;

    public UpdateClientUseCase(IClient repository, ClientMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<ClientDTO> invoke(Long clientId, ClientDTO clientDTO) {
        return repository.findClient(clientId)
                .map(existingClient -> {
                    ClientBO existingBO = mapper.toBO(existingClient);
                    ClientBO updateBO = mapper.toBO(clientDTO);

                    validateUpdate(existingBO, updateBO);

                    ClientDTO validatedDTO = mapper.toDTO(updateBO);

                    return repository.updateClient(clientId, validatedDTO);
                })
                .flatMap(dto -> dto);
    }

    private void validateUpdate(ClientBO existing, ClientBO update) {
    }
}
