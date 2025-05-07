package br.com.aviapp.api.domain.enums;

public enum EnumEggType {
    CLEAN("Limpo"),
    DIRTY("Sujo"),
    DOUBLE_YOLK("Duas gemas"),
    CRACKED("Trincado"),
    THIN_SHELL("Casca fina");

    private final String description;

    EnumEggType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
