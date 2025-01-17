package br.com.aviapp.api.domain.entities;

import lombok.Getter;

@Getter
public class AviaryBO {
    
    private Long id;
    private String name;
    private BatchBO batchId;
    
    public AviaryBO(Long id, String name, BatchBO batchId) {
        this.id = id;
        this.name = name;
        this.batchId = batchId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
}
