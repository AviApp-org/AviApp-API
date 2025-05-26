package br.com.aviapp.api.domain.enums;

public enum EnumEggType {
    CLEAN("Limpo"),
    NEST_DIRTY("Sujo de ninho"),
    BED_DIRTY("Sujo de cama"),
    BROKEN("Quebrado"),
    CRACKED("Trincado"),
    SMALL("Pequeno"),
    DOUBLE_YOLK("Duas gemas"),
    THIN_SHELL("Casca fina");

    private final String description;

    EnumEggType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
