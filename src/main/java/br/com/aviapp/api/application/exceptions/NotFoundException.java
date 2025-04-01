package br.com.aviapp.api.application.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Busca com ID " + id + " não foi encontrado");
    }
}
