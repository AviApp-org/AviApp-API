package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.mappers.ClientMapper;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;
import br.com.aviapp.api.infra.postgresql.repository.PgSqlClientRepository;
import br.com.aviapp.api.presentation.dto.request.client.CreateClientRequestDTO;

@Service
public class ClientService {

    private PgSqlClientRepository repository;

    @Autowired
    public ClientService(PgSqlClientRepository clientRepository) {
        this.repository = clientRepository;
    }

    public List<ClientDTO> findAll() {
        List<PgSqlClientEntity> entities = repository.findAll();
        // return entities.stream().map(e -> ClientMapper.toDTO(e)).toList();
        return null;
    }

    public ClientDTO findById(Long id) {
        Optional<MySqlClientEntity> clientEntityOptional = repository.findById(id);
        if (clientEntityOptional.isPresent()) {
            MySqlClientEntity clientEntity = clientEntityOptional.get();
            return toClientDTO(clientEntity);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    public ClientBO save(CreateClientRequestDTO dto) {
        ClientBO bo = ClientMapper.toBO(dto);

        PgSqlClientEntity entity = ClientMapper.toPgSqlEntity(bo);
        PgSqlClientEntity savedClient = repository.save(entity);

        ClientBO response = ClientMapper.toBO(savedClient);

        return response;
    }

    public void deleteClient(Long id) {
        var clientExiste = repository.existsById(id);

        if (clientExiste) {
            repository.deleteById(id);
        }
    }

    public Optional<MySqlClientEntity> getClientById(Long id) {
        var client = repository.findById(id);
        return client;
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Optional<MySqlClientEntity> optionalClient = repository.findById(id);

        if (optionalClient.isPresent()) {
            MySqlClientEntity existingClient = optionalClient.get();

            existingClient.setName(clientDTO.getName());
            existingClient.setEmail(clientDTO.getEmail());
            existingClient.setCpf(clientDTO.getCpf());
            existingClient.setTelefone(clientDTO.getTelefone());
            existingClient.setStatus(clientDTO.getStatus());

            MySqlClientEntity updatedClient = repository.save(existingClient);

            return toClientDTO(updatedClient);
        } else {
            throw new RuntimeException("Cliente não encontrado com o ID: " + id);
        }
    }
}
