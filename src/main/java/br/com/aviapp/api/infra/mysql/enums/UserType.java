package br.com.aviapp.api.infra.mysql.enums;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("Administrador"),
    EMPLOYEE("Funcionário"),
    MANAGER("Gerente");

    private final String description;

    UserType(String description) {
        this.description = description;
    }
}
