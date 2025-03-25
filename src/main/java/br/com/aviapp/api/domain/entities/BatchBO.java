package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;

@Getter
public class BatchBO {

    private final Long id;
    private String name;
    private final Date startDate;
    private EnumBatchStatus status;
    private final FarmBO farm;

    public BatchBO(Long id, String name, Date startDate, EnumBatchStatus status, FarmBO farm) throws IllegalArgumentException {
        ParamValidator.validate(name, startDate, status, farm);
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateStr = sdf.format(startDate);

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            if (dateStr.matches("\\d{2}-\\d{2}-\\d{4}") && Integer.parseInt(dateStr.substring(6)) <= currentYear) {
                this.id = id;
                this.name = name;
                this.startDate = startDate;
                this.status = status;
                this.farm = farm;
            } else {
                throw new IllegalArgumentException("Data inválida");
            }
        } catch (InvalidParamError e) {
            throw new IllegalArgumentException("Erro ao criar o lote: " + e.getMessage());
        }


    }



    public void desativar() {
        this.status = EnumBatchStatus.INACTIVE;
    }

    public void ativar() {
        this.status = EnumBatchStatus.ACTIVE;
    }

    public void setName(String name) {
        this.name = name;
    }
}
