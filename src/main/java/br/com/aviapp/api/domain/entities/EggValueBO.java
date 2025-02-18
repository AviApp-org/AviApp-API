package br.com.aviapp.api.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumEggType;
import lombok.Getter;

@Getter
public class EggValueBO {

    private Long id;

    private EnumEggType eggType;

    private LocalDateTime timestamp;

    private BigDecimal value;

    public EggValueBO(Long id, EnumEggType eggType, LocalDateTime timestamp, BigDecimal value) {
        this.id = id;
        this.eggType = eggType;
        this.timestamp = timestamp;
        this.value = value;
    }

    public void setEggType(EnumEggType eggType) {
        this.eggType = eggType;
    }

}
