package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.CollectChickenBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.entities.EggDetailBO;

import java.util.List;

public abstract class CollectCalculator {

    static int calculateTotalEggs(List<CollectEggBO> collectEggs) {
        if (collectEggs == null) return 0;

        int totalEggs = 0;

        for (CollectEggBO egg : collectEggs) {
            List<EggDetailBO> eggDetails = egg.getEggDetails();
            if (eggDetails != null) {
                totalEggs += eggDetails.stream().mapToInt(EggDetailBO::getQuantity).sum();
            }
        }

        return totalEggs;
    }


    static int calculateDeadChickens(List<CollectChickenBO> collectChickens) {
        if (collectChickens == null) return 0;
        return collectChickens.stream().mapToInt(CollectChickenBO::getDeadChickens).sum();
    }

    static int calculateDeadRoosters(List<CollectChickenBO> collectChickens) {
        if (collectChickens == null) return 0;
        return collectChickens.stream().mapToInt(CollectChickenBO::getDeadRoosters).sum();
    }

}
