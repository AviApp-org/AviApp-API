package br.com.aviapp.api.domain.enums;

import lombok.Getter;

@Getter
public enum EnumUserType {

    ADMIN("Administrador"),
    EMPLOYEE("Funcionário"),
    MANAGER("Gerente");

    private final String description;

    EnumUserType(String description) {
        this.description = description;
    }



}
