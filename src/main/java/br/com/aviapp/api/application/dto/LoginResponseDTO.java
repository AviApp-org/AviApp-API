package br.com.aviapp.api.application.dto;

public record LoginResponseDTO(
        String token,
        String refreshToken,
        Long clientId,
        String clientName,
        String role,
        String login
) {}
