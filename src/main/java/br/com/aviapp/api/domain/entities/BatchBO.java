package br.com.aviapp.api.domain.entities;

public class BatchBO {
    
    private Long id;
    private FarmBO farm;
    
    public BatchBO() {
    }

    public BatchBO(Long id, FarmBO farm) {
        this.id = id;
        this.farm = farm;
    }

    public Long getId() {
        return id;
    }

    public FarmBO getFarm() {
        return farm;
    }
    
}
