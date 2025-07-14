package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.UserCredentialsDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserCredentials {

    UserDetails findByLogin(String login);

    UserCredentialsDTO register(UserCredentialsDTO userCredentialsDTO);
}
