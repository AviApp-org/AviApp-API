package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.BCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.usecases.client.ActivateClientUseCase;
import br.com.aviapp.api.application.usecases.client.CreateClientUseCase;
import br.com.aviapp.api.application.usecases.client.DeactivateClientUseCase;
import br.com.aviapp.api.application.usecases.client.DeleteClientUseCase;
import br.com.aviapp.api.application.usecases.client.FindClientByIdUseCase;
import br.com.aviapp.api.application.usecases.client.ListClientsUseCase;
import br.com.aviapp.api.application.usecases.client.UpdateClientUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/clients")
public class ClientController {

    private final CreateClientUseCase createClient;
    private final DeleteClientUseCase deleteClient;
    private final FindClientByIdUseCase findClientById;
    private final ListClientsUseCase listAllClients;
    private final UpdateClientUseCase updateClient;
    private final ActivateClientUseCase activateClientUseCase;
    private final DeactivateClientUseCase deactivateClientUseCase;

    public ClientController(CreateClientUseCase createClient, DeleteClientUseCase deleteClient,
            FindClientByIdUseCase findClientById, ListClientsUseCase listAllClients, UpdateClientUseCase updateClient,
            ActivateClientUseCase activateClientUseCase, DeactivateClientUseCase deactivateClientUseCase) {
        this.createClient = createClient;
        this.deleteClient = deleteClient;
        this.findClientById = findClientById;
        this.listAllClients = listAllClients;
        this.updateClient = updateClient;
        this.activateClientUseCase = activateClientUseCase;
        this.deactivateClientUseCase = deactivateClientUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = listAllClients.invoke();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Optional<ClientDTO> client = findClientById.invoke(id);
        return client.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO newClient = createClient.invoke(clientDTO);
        URI location = URI.create("/api/Clientes/" + newClient.id());
        return ResponseEntity.created(location).body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        Optional<ClientDTO> updatedClient = updateClient.invoke(id, clientDTO);
        return updatedClient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        deleteClient.invoke(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{clientId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateClient(@PathVariable Long clientId) {
        try {
            activateClientUseCase.invoke(clientId);
        } catch (BCException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/{clientId}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateClient(@PathVariable Long clientId) {
        try {
            deactivateClientUseCase.invoke(clientId);
        } catch (BCException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
