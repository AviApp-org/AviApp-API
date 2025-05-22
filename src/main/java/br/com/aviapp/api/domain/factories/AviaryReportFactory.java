package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.*;

import java.math.BigDecimal;
import java.util.List;

public class AviaryReportFactory extends CollectCalculator{

    public static AviaryReportBO createFromAviary(AviaryBO aviary, List<CollectEggBO> collectEggs, List<CollectChickenBO> collectChickens) {
        int totalEggsCollected = calculateTotalEggs(collectEggs);
        int totalDeadChickens = calculateDeadChickens(collectChickens);
        int totalDeadRoosters = calculateDeadRoosters(collectChickens);
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviary.getCurrentAmountOfChickens();
        int currentRoosters = aviary.getCurrentAmountOfRoosters();
        int totalBirds = currentChickens + currentRoosters;
        BigDecimal production = BigDecimal.valueOf(totalEggsCollected / currentChickens);
        BigDecimal roosterMortality= BigDecimal.valueOf(totalDeadRoosters / currentRoosters);
        BigDecimal chickenMortality = BigDecimal.valueOf(totalDeadChickens / currentChickens);
        BigDecimal mortality = BigDecimal.valueOf(totalDeadBirds / totalBirds);
        BigDecimal chickenRoosterProportion = BigDecimal.valueOf(currentChickens / currentRoosters);

        List<EggDetailBO> quantityByEggType = calculateEggsByType(collectEggs);

        return new AviaryReportBO(
                aviary.getName(),
                collectEggs,
                collectChickens,
                totalEggsCollected,
                totalDeadBirds,
                totalDeadChickens,
                totalDeadRoosters,
                currentChickens,
                currentRoosters,
                totalBirds,
                production,
                roosterMortality,
                chickenMortality,
                mortality,
                chickenRoosterProportion,
                quantityByEggType
        );
    }
}

