package br.com.aviapp.api.application.dto;

public record LoginResponseDTO (String token,
                                Long clientId,
                                String clientName,
                                String userRole,
                                String login) {
}
