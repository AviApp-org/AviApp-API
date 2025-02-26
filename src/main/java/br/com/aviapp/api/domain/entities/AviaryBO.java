package br.com.aviapp.api.domain.entities;

import lombok.Getter;

@Getter
public class AviaryBO {

    private Long id;
    private String name;
    private Integer intialTotalAmount;
    private Integer initialAmountOfRoosters;
    private Integer initialAmountOfChickens;
    private BatchBO batchId;

    public AviaryBO(Long id, String name, Integer intialTotalAmount, Integer initialAmountOfRoosters, Integer initialAmountOfChickens, BatchBO batchId) {
        this.id = id;
        this.name = name;
        this.intialTotalAmount = intialTotalAmount;
        this.initialAmountOfRoosters = initialAmountOfRoosters;
        this.initialAmountOfChickens = initialAmountOfChickens;
        this.batchId = batchId;
    }

    public void setName(String name) {
        this.name = name;
    }


}
