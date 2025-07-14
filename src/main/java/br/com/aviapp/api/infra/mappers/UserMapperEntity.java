package br.com.aviapp.api.infra.mappers;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.UserCredentialsDTO;
import br.com.aviapp.api.domain.enums.EnumUserType;
import br.com.aviapp.api.infra.mysql.enums.UserType;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlUserCredentials;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapperEntity {

    private final ClientRepositoryJPA clientRepositoryJPA;

    public UserMapperEntity(ClientRepositoryJPA clientRepositoryJPA) {
        this.clientRepositoryJPA = clientRepositoryJPA;
    }


    public UserCredentialsDTO toDTO(MySqlUserCredentials entity) {
        return new UserCredentialsDTO(
                entity.getId(),
                entity.getClient().getId(),
                entity.getLogin(),
                entity.getPassword(),
                EnumUserType.valueOf(entity.getRole().name())
        );
    }

    public MySqlUserCredentials toEntity(UserCredentialsDTO dto) {
        Optional<MySqlClientEntity> clientEntity = clientRepositoryJPA.findById(dto.clientId());

        return new MySqlUserCredentials(
                dto.id(),
                clientEntity.get(),
                dto.login(),
                dto.password(),
                UserType.valueOf(dto.role().name())
        );
    }

    public List<UserCredentialsDTO> toDTOList(List<MySqlUserCredentials> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlUserCredentials> toEntityList(List<UserCredentialsDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
