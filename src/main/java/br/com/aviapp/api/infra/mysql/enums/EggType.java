package br.com.aviapp.api.infra.mysql.enums;

import lombok.Getter;

@Getter
public enum EggType {

    CLEAN("Limpo"),
    NEST_DIRTY("Sujo de ninho"),
    BED_DIRTY("Sujo de cama"),
    BROKEN("Quebrado"),
    CRACKED("Trincado"),
    SMALL("Pequeno"),
    DOUBLE_YOLK("Duas gemas"),
    THIN_SHELL("Casca fina");

    private final String description;

    EggType(String description) {
        this.description = description;
    }

}
