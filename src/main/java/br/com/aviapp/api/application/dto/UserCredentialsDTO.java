package br.com.aviapp.api.application.dto;


import java.time.LocalDateTime;

public record UserCredentialsDTO (Long clientId, String username, String password, LocalDateTime created_at){
}
