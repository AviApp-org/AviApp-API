package br.com.aviapp.api.application.usecases.client;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.IClient;
import br.com.aviapp.api.application.mappers.ClientMapperBO;

public class ListClientsUseCase {

    private final IClient repository;
    private final ClientMapperBO mapper;

    public ListClientsUseCase(IClient repository, ClientMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ClientDTO> invoke() {
        List<ClientDTO> clients = repository.listAllClients();
        return clients.stream()
                .map(mapper::toBO)
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
