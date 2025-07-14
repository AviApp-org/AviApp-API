package br.com.aviapp.api.application.usecases.userCredentials;

import br.com.aviapp.api.application.dto.UserCredentialsDTO;
import br.com.aviapp.api.application.gateways.IUserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterUserUseCase {

    private final IUserCredentials repository;

    private final FindByLoginUseCase findByLoginUseCase;

    public RegisterUserUseCase(IUserCredentials repository, FindByLoginUseCase findByLoginUseCase) {
        this.repository = repository;
        this.findByLoginUseCase = findByLoginUseCase;
    }

    public UserCredentialsDTO invoke(UserCredentialsDTO credentialsDTO) {

        if (this.findByLoginUseCase.invoke(credentialsDTO.login()) != null) throw new RuntimeException("Usuário já cadastrado");

        String password = new BCryptPasswordEncoder().encode(credentialsDTO.password());

        return repository.register(new UserCredentialsDTO(
                credentialsDTO.id(),
                credentialsDTO.clientId(),
                credentialsDTO.login(),
                password,
                credentialsDTO.role()
        ));
    }
}
