package br.com.aviapp.api.application.dto;


import br.com.aviapp.api.domain.enums.EnumUserType;

public record UserCredentialsDTO (Long id, Long clientId, String login, String password, EnumUserType role){
}
