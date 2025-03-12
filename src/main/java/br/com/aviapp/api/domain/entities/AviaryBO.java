package br.com.aviapp.api.domain.entities;

import lombok.Getter;

@Getter
public class AviaryBO {

    private Long id;
    private String name;
    private Integer initialAmountOfRoosters;
    private Integer initialAmountOfChickens;
    private BatchBO batchId;

    public AviaryBO(Long id, String name, Integer initialAmountOfRoosters, Integer initialAmountOfChickens, BatchBO batchId) {

        if (initialAmountOfRoosters < 0 || initialAmountOfChickens < 0) {
            throw new IllegalArgumentException("Initial amount of roosters and chickens must be non-negative.");
        }

        this.id = id;
        this.name = name;
        this.initialAmountOfRoosters = initialAmountOfRoosters;
        this.initialAmountOfChickens = initialAmountOfChickens;
        this.batchId = batchId;
    }

    public void setName(String name) {
        this.name = name;
    }


}