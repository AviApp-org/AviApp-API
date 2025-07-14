package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.dto.LoginDTO;
import br.com.aviapp.api.application.dto.LoginResponseDTO;
import br.com.aviapp.api.application.dto.UserCredentialsDTO;
import br.com.aviapp.api.application.usecases.userCredentials.FindByLoginUseCase;
import br.com.aviapp.api.application.usecases.userCredentials.RegisterUserUseCase;
import br.com.aviapp.api.infra.mysql.models.MySqlUserCredentials;
import br.com.aviapp.api.infra.services.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FindByLoginUseCase findByLoginUseCase;

    @Autowired
    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        MySqlUserCredentials userCredentials = (MySqlUserCredentials) auth.getPrincipal();
        var token = tokenService.generateToken(userCredentials);

        LoginResponseDTO response = new LoginResponseDTO(
                token,
                userCredentials.getClient().getId(),
                userCredentials.getClient().getName(),
                userCredentials.getRole().name(),
                userCredentials.getLogin()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserCredentialsDTO> register (@RequestBody @Valid UserCredentialsDTO credentialsDTO) {
        UserCredentialsDTO newUser = registerUserUseCase.invoke(credentialsDTO);
        URI location = URI.create("/api/auth/register/" + newUser.id());
        return ResponseEntity.created(location).body(newUser);
    }
}
