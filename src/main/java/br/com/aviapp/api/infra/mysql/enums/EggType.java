package br.com.aviapp.api.infra.mysql.enums;

public enum EggType {

    CLEAN("Limpo"),
    DIRTY("Sujo"),
    DOUBLE_YOLK("Duas gemas"),
    CRACKED("Trincado"),
    THIN_SHELL("Casca fina");

    private final String description;

    EggType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
