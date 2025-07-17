package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.CreationValidator;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;

@Getter
public class BatchBO implements CreationValidator {

    private final Long id;
    private String name;
    private final Date startDate;
    private EnumBatchStatus status;
    private final FarmBO farm;

    public BatchBO(Long id, String name, Date startDate, EnumBatchStatus status, FarmBO farm) throws IllegalArgumentException {
        try {
            ParamValidator.validate(name, startDate, status, farm);

            this.id = id;
            this.name = name;
            this.startDate = startDate;
            this.status = status;
            this.farm = farm;
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

    @Override
    public void validateForCreation() throws BusinessRuleException {
        // Validação do formato da data
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = sdf.format(startDate);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (!dateStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new BusinessRuleException("Formato de data inválido. Use o formato dd-MM-yyyy");
        }

        if (Integer.parseInt(dateStr.substring(6)) > currentYear) {
            throw new BusinessRuleException("A data de início não pode ser no futuro");
        }
    }
}
