package br.com.aviapp.api.application.usecases.userCredentials;

import br.com.aviapp.api.application.dto.UserCredentialsDTO;
import br.com.aviapp.api.application.gateways.IUserCredentials;
import org.springframework.security.core.userdetails.UserDetails;

public class FindByLoginUseCase {

    private final IUserCredentials userCredentialsRepository;
    public FindByLoginUseCase(IUserCredentials userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public UserDetails invoke(String login) {
        return userCredentialsRepository.findByLogin(login);
    }
}
