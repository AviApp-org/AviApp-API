package br.com.aviapp.api.domain.entities;

import lombok.Getter;


import java.util.Date;

@Getter
public class BatchBO {
    
    private Long id;
    private String name;
    private Date startDate;
    private boolean isActive;
    private FarmBO farm;

    public BatchBO(Long id, String name, Date startDate, boolean isActive, FarmBO farm) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.isActive = isActive;
        this.farm = farm;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
