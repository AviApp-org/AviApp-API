package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.mappers.ClientMapper;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.repository.ClientRepository;
import br.com.aviapp.api.presentation.dto.request.client.CreateClientRequestDTO;
import br.com.aviapp.api.presentation.dto.response.client.CreateClientResponseDTO;

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

    public MySqlClientEntity save(CreateClientRequestDTO dto) {
        ClientBO bo = ClientMapper.toBO(dto);

        bo.isCpfValid();
        bo.isBirthDateValid();

        MySqlClientEntity entity = ClientMapper.toEntity(bo);
        MySqlClientEntity savedClient = clientRepository.save(entity);

        CreateClientResponseDTO response = ClientMapper.toDTO(entity);

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
