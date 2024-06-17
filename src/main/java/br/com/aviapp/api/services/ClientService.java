package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<MySqlClientEntity> findAll() {
        return clientRepository.findAll();
    }

    public ClientDTO findById(Long id) {
        Optional<MySqlClientEntity> clientEntityOptional = clientRepository.findById(id);
        if (clientEntityOptional.isPresent()) {
            MySqlClientEntity clientEntity = clientEntityOptional.get();
            return toClientDTO(clientEntity);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    public Long save(ClientDTO clientDTO) {

        var entity = new MySqlClientEntity(
                null,
                clientDTO.getName(),
                clientDTO.getEmail(),
                clientDTO.getCpf(),
                clientDTO.getTelefone(),
                clientDTO.getStatus());

        var savedClient = clientRepository.save(entity);
        return savedClient.getId();
    }

    public ClientDTO toClientDTO(MySqlClientEntity mySqlClientEntity) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(mySqlClientEntity.getId());
        clientDTO.setName(mySqlClientEntity.getName());
        clientDTO.setEmail(mySqlClientEntity.getEmail());
        clientDTO.setCpf(mySqlClientEntity.getCpf());
        clientDTO.setTelefone(mySqlClientEntity.getTelefone());
        clientDTO.setStatus(mySqlClientEntity.getStatus());
        // Mapear outros campos, se necessário
        return clientDTO;
    }

    public void deleteClient(Long id) {
        var clientExiste = clientRepository.existsById(id);

        if (clientExiste) {
            clientRepository.deleteById(id);
        }
    }

    public List<ClientDTO> listClients() {
        List<MySqlClientEntity> clients = clientRepository.findAll();
        return clients.stream()
                .map(this::toClientDTO)
                .collect(Collectors.toList());
    }

    public Optional<MySqlClientEntity> getClientById(Long id) {
        var client = clientRepository.findById(id);
        return client;
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Optional<MySqlClientEntity> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            MySqlClientEntity existingClient = optionalClient.get();

            existingClient.setName(clientDTO.getName());
            existingClient.setEmail(clientDTO.getEmail());
            existingClient.setCpf(clientDTO.getCpf());
            existingClient.setTelefone(clientDTO.getTelefone());
            existingClient.setStatus(clientDTO.getStatus());

            MySqlClientEntity updatedClient = clientRepository.save(existingClient);

            return toClientDTO(updatedClient);
        } else {
            throw new RuntimeException("Cliente não encontrado com o ID: " + id);
        }
    }
}
