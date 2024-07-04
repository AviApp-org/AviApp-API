package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.mappers.ClienteMapper;
import br.com.aviapp.api.infra.mysql.enums.ClientStatusType;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> findAll() {
        List<MySqlClientEntity> entities = clientRepository.findAll();
        return entities.stream().map(e -> ClienteMapper.toDTO(e)).toList();
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

    public MySqlClientEntity save(ClientDTO clientDTO) {

        if (clientDTO.getName().isBlank()) {
            throw new RuntimeException("Campo obrigatório vazio: nome");
        }

        if (clientDTO.getCpf().isBlank()) {
            throw new RuntimeException("Campo obrigatório vazio: cpf");
        }

        if (clientDTO.getEmail().isBlank()) {
            throw new RuntimeException("Campo obrigatório vazio: email");
        }

        if (clientDTO.getStatus().toString().isBlank()) {
            clientDTO.setStatus(ClientStatusType.ACTIVE);
        }

        MySqlClientEntity entity = new MySqlClientEntity(
                null,
                clientDTO.getName(),
                clientDTO.getCpf(),
                clientDTO.getEmail(),
                clientDTO.getTelefone(),
                clientDTO.getStatus());

        MySqlClientEntity savedClient = clientRepository.save(entity);
        return savedClient;
    }

    public void deleteClient(Long id) {
        var clientExiste = clientRepository.existsById(id);

        if (clientExiste) {
            clientRepository.deleteById(id);
        }
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
