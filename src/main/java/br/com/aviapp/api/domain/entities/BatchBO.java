package br.com.aviapp.api.domain.entities;

import lombok.Getter;

@Getter
public class BatchBO {
    
    private Long id;
    private FarmBO farm;
    
    public BatchBO() {
    }

    public BatchBO(Long id, FarmBO farm) {
        this.id = id;
        this.farm = farm;
    }

}
