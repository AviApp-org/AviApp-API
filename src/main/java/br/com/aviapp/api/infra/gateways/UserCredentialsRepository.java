package br.com.aviapp.api.infra.gateways;

import br.com.aviapp.api.application.dto.UserCredentialsDTO;
import br.com.aviapp.api.application.gateways.IUserCredentials;
import br.com.aviapp.api.infra.mappers.UserMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlUserCredentials;
import br.com.aviapp.api.infra.mysql.repository.UserCredentialsRepositoryJPA;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCredentialsRepository implements IUserCredentials {

    private final UserCredentialsRepositoryJPA repository;
    private final UserMapperEntity mapper;

    public UserCredentialsRepository(UserCredentialsRepositoryJPA repository, UserMapperEntity mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public UserCredentialsDTO register(UserCredentialsDTO userCredentialsDTO) {
        MySqlUserCredentials userEntity = mapper.toEntity(userCredentialsDTO);
        MySqlUserCredentials savedUserEntity = repository.save(userEntity);
        return mapper.toDTO(savedUserEntity);
    }


}
