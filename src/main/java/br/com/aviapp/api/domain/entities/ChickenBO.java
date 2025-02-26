package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

public class ChickenBO {
    
    private Long id;

    
    private Integer currentRoosters;
    
    private Integer currentChickens;
    
    private Integer currentAmount;
    
    public ChickenBO(Long id, Integer currentRoosters, Integer currentChickens, Integer currentAmount) 
    throws InvalidParamError{
        ParamValidator.validate(currentRoosters, currentChickens, currentAmount);
        this.id = id;
        this.currentRoosters = currentRoosters;
        this.currentChickens = currentChickens;
        this.currentAmount = currentAmount;
    }
    
    public Long getId() {
        return id;
    }

    public Integer getCurrentRoosters() {
        return currentRoosters;
    }

    public Integer getCurrentChickens() {
        return currentChickens;
    }

    public Integer getCurrentAmount() {
        return currentAmount;
    }

    public void deathOfRooster(Integer amount) {
        this.currentRoosters -= amount;
        this.attTotalAmount();
    }

    public void deathOfChicken(Integer amount) {
        this.currentChickens -= amount;
        this.attTotalAmount();
    }

    public void attTotalAmount() {
        this.currentAmount = this.currentRoosters + this.currentChickens;
    }
}
