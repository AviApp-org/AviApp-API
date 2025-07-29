package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.LoginDTO;
import br.com.aviapp.api.application.dto.LoginResponseDTO;
import br.com.aviapp.api.application.dto.TokenRefreshDTO;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
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
        var accessToken = tokenService.generateToken(userCredentials);
        var refreshToken = tokenService.generateRefreshToken(userCredentials);

        LoginResponseDTO response = new LoginResponseDTO(
                accessToken,
                refreshToken,
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

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody @Valid TokenRefreshDTO dto) {
        String login = tokenService.validateToken(dto.refreshToken());
        if (login == null || login.isEmpty()) {
            return ResponseEntity.status(401).build(); // Token inválido
        }

        MySqlUserCredentials user = (MySqlUserCredentials) findByLoginUseCase.invoke(login);

        String newAccessToken = tokenService.generateToken(user);
        String newRefreshToken = tokenService.generateRefreshToken(user); // opcional

        return ResponseEntity.ok(new LoginResponseDTO(
                newAccessToken,
                newRefreshToken,
                user.getClient().getId(),
                user.getClient().getName(),
                user.getRole().name(),
                user.getLogin()
        ));
    }

}
