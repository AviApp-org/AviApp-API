package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.ClientDTO;

public interface ClientRepository {
    ClientDTO createClient(ClientDTO client);

    List<ClientDTO> listAllClients();

    Optional<ClientDTO> findClient(Long clientID);

    void deleteClient(Long clientID);

    Optional<ClientDTO> updateClient(Long clientID, ClientDTO updatedClientDTO);

}
