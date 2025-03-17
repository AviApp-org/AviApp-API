package br.com.aviapp.api.domain.entities;

import lombok.Getter;

import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;

@Getter
public class BatchBO {

    private Long id;
    private String name;
    private Date startDate;
    private EnumBatchStatus status;
    private FarmBO farm;

    public BatchBO(Long id, String name, Date startDate, EnumBatchStatus status, FarmBO farm) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.status = status;
        this.farm = farm;
    }

    public void desativar() {
        this.status = EnumBatchStatus.INACTIVE;
    }

    public void ativar() {
        this.status = EnumBatchStatus.ACTIVE;
    }
}
