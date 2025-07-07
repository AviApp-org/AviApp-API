package br.com.aviapp.api.application.usecases.client;

import br.com.aviapp.api.application.gateways.IClient;

public class DeleteClientUseCase {

    private final IClient repository;

    public DeleteClientUseCase(IClient repository) {
        this.repository = repository;
    }

    public void invoke(Long clientID) {
        // Verify if client exists and convert to BO for any business rules
      repository.deleteClient(clientID);
    }
}
