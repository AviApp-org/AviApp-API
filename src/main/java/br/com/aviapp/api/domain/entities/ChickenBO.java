package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;
import lombok.Getter;

@Getter
public class ChickenBO {
    
    private Long id;

    private Integer currentRoosters;
    
    private Integer currentChickens;
    

    public ChickenBO(Long id, Integer currentRoosters, Integer currentChickens)
    throws InvalidParamError{
        ParamValidator.validate(currentRoosters, currentChickens);

        if (currentRoosters < 0 || currentChickens < 0 ) {
            throw new InvalidParamError("Não é permitido valores negativos para o número de galinhas, galos ou total de galinhas.");
        }

        this.id = id;
        this.currentRoosters = currentRoosters;
        this.currentChickens = currentChickens;
    }


    public void deathOfRooster(Integer amount) {
        this.currentRoosters -= amount;
    }

    public void deathOfChicken(Integer amount) {
        this.currentChickens -= amount;
    }

    public Integer getTotalAmount() {
        return  currentRoosters + currentChickens;
    }
}
