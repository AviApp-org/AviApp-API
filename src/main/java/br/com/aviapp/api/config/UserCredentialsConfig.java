package br.com.aviapp.api.config;

import br.com.aviapp.api.application.gateways.IUserCredentials;
import br.com.aviapp.api.application.usecases.userCredentials.FindByLoginUseCase;
import br.com.aviapp.api.application.usecases.userCredentials.RegisterUserUseCase;
import br.com.aviapp.api.infra.gateways.UserCredentialsRepository;
import br.com.aviapp.api.infra.mappers.UserMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.UserCredentialsRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCredentialsConfig {

    @Bean
    public FindByLoginUseCase findByLoginUseCase(IUserCredentials userCredentialsRepository) {
        return new FindByLoginUseCase(userCredentialsRepository);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(IUserCredentials userCredentialsRepository, FindByLoginUseCase findByLoginUseCase) {
        return new RegisterUserUseCase(userCredentialsRepository,findByLoginUseCase);
    }

    @Bean
    public UserMapperEntity userMapperEntity(ClientRepositoryJPA repositoryJPA) {
        return new UserMapperEntity(repositoryJPA);
    }

    @Bean
    public IUserCredentials userCredentialsRepository(UserMapperEntity mapper, UserCredentialsRepositoryJPA repository) {
        return new UserCredentialsRepository(repository, mapper);
    }
}
