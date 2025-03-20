package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.gateways.ClientRepository;
import br.com.aviapp.api.infra.mappers.ClientMapperEntity;
import br.com.aviapp.api.infra.mysql.enums.ClientStatusType;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientRepositoryJPA repositoryJPA;
    private final ClientMapperEntity mapperEntity;

    public ClientRepositoryImpl(ClientRepositoryJPA repositoryJPA, ClientMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
    }

    @Override
    public ClientDTO createClient(ClientDTO client) {
        MySqlClientEntity entity = mapperEntity.toEntity(client);
        MySqlClientEntity savedEntity = repositoryJPA.save(entity);
        return mapperEntity.toDTO(savedEntity);
    }

    @Override
    public List<ClientDTO> listAllClients() {
        return mapperEntity.toDTOList(repositoryJPA.findAll());

    }

    @Override
    public Optional<ClientDTO> findClient(Long clientID) {
        return repositoryJPA.findById(clientID)
                .map(mapperEntity::toDTO);
    }

    @Override
    public void deleteClient(Long clientID) {
        repositoryJPA.deleteById(clientID);

    }

    @Override
    public Optional<ClientDTO> updateClient(Long clientID, ClientDTO updatedClientDTO) {
        return repositoryJPA.findById(clientID)
                .map(existingClient -> {
                    if (updatedClientDTO.name() != null) {
                        existingClient.setName(updatedClientDTO.name());
                    }
                    if (updatedClientDTO.cnpj() != null) {
                        existingClient.setCnpj(updatedClientDTO.cnpj());
                    }
                    if (updatedClientDTO.email() != null) {
                        existingClient.setEmail(updatedClientDTO.email());
                    }
                    if (updatedClientDTO.phone() != null) {
                        existingClient.setPhone(updatedClientDTO.phone());
                    }
                    if (updatedClientDTO.status() != null) {
                        existingClient.setStatus(ClientStatusType.valueOf(updatedClientDTO.status().name()));
                    }

                    MySqlClientEntity savedEntity = repositoryJPA.save(existingClient);
                    return mapperEntity.toDTO(savedEntity);
                });
    }

    @Override
    public void save(ClientDTO clientDTO) {
        MySqlClientEntity entity = mapperEntity.toEntity(clientDTO);
        repositoryJPA.save(entity);
    }

}
