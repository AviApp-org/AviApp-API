package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;

public class EggDetailPercentageVO extends EggDetailBO {
    private double percentage;

    public EggDetailPercentageVO(EnumEggType type, Integer quantity, double percentage) throws InvalidParamError {
        super(type, quantity);
        this.percentage = percentage;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
