package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.ClientRepository;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.application.usecases.client.ActivateClientUseCase;
import br.com.aviapp.api.application.usecases.client.CreateClientUseCase;
import br.com.aviapp.api.application.usecases.client.DeactivateClientUseCase;
import br.com.aviapp.api.application.usecases.client.DeleteClientUseCase;
import br.com.aviapp.api.application.usecases.client.FindClientByIdUseCase;
import br.com.aviapp.api.application.usecases.client.ListClientsUseCase;
import br.com.aviapp.api.application.usecases.client.UpdateClientUseCase;
import br.com.aviapp.api.infra.mappers.ClientMapperEntity;

@Configuration
public class ClientConfig {

    @Bean   
    ActivateClientUseCase activateClient(ClientRepository clientRepository, ClientMapperBO mapperBO) {
        return new ActivateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    DeactivateClientUseCase deactivateClient(ClientRepository clientRepository, ClientMapperBO mapperBO) {
        return new DeactivateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    CreateClientUseCase createCliente(ClientRepository clientRepository, ClientMapperBO mapperBO) {
        return new CreateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    DeleteClientUseCase deleteClient(ClientRepository clientRepository) {
        return new DeleteClientUseCase(clientRepository);
    }

    @Bean
    FindClientByIdUseCase findClient(ClientRepository clientRepository, ClientMapperBO mapperBO) {
        return new FindClientByIdUseCase(clientRepository, mapperBO);
    }

    @Bean
    ListClientsUseCase listAllClients(ClientRepository clientRepository, ClientMapperBO mapperBO) {
        return new ListClientsUseCase(clientRepository, mapperBO);
    }

    @Bean
    UpdateClientUseCase updateClient(ClientRepository clientRepository, ClientMapperBO mapperBO) {
        return new UpdateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    ClientMapperEntity returnClientEntityMapper() {
        return new ClientMapperEntity();
    }

    @Bean
    ClientMapperBO returnClientMapperBO() {
        return new ClientMapperBO();
    }
}
