package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.IClient;
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
    public ActivateClientUseCase activateClient(IClient clientRepository, ClientMapperBO mapperBO) {
        return new ActivateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    public DeactivateClientUseCase deactivateClient(IClient clientRepository, ClientMapperBO mapperBO) {
        return new DeactivateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    public CreateClientUseCase createCliente(IClient clientRepository, ClientMapperBO mapperBO) {
        return new CreateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    public DeleteClientUseCase deleteClient(IClient clientRepository) {
        return new DeleteClientUseCase(clientRepository);
    }

    @Bean
    public FindClientByIdUseCase findClient(IClient clientRepository, ClientMapperBO mapperBO) {
        return new FindClientByIdUseCase(clientRepository, mapperBO);
    }

    @Bean
    public ListClientsUseCase listAllClients(IClient clientRepository, ClientMapperBO mapperBO) {
        return new ListClientsUseCase(clientRepository, mapperBO);
    }

    @Bean
    public UpdateClientUseCase updateClient(IClient clientRepository, ClientMapperBO mapperBO) {
        return new UpdateClientUseCase(clientRepository, mapperBO);
    }

    @Bean
    public ClientMapperEntity returnClientEntityMapper() {
        return new ClientMapperEntity();
    }

    @Bean
    public ClientMapperBO returnClientMapperBO() {
        return new ClientMapperBO();
    }
}
