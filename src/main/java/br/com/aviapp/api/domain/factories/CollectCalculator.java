package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.CollectChickenBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.enums.EnumEggType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    protected static List<EggDetailBO> calculateEggsByType(List<CollectEggBO> collectEggs) {
        Map<String, Integer> eggTypeQuantityMap = new HashMap<>();

        for (CollectEggBO collectEgg : collectEggs) {
            for (EggDetailBO eggDetail : collectEgg.getEggDetails()) {
                String type = String.valueOf(eggDetail.getType());
                int quantity = eggDetail.getQuantity();

                eggTypeQuantityMap.put(type, eggTypeQuantityMap.getOrDefault(type, 0) + quantity);
            }
        }

        return eggTypeQuantityMap.entrySet().stream()
                .map(entry -> new EggDetailBO(EnumEggType.valueOf(entry.getKey()), entry.getValue())).collect(Collectors.toList());
    }
}
