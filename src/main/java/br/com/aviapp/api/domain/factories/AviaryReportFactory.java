package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.*;

import java.util.List;

public class AviaryReportFactory extends CollectCalculator {

    public static AviaryReportBO createFromAviary(AviaryBO aviary, List<CollectEggBO> collectEggs, List<CollectChickenBO> collectChickens) {
        int totalEggsCollected = calculateTotalEggs(collectEggs);
        int totalDeadChickens = calculateDeadChickens(collectChickens);
        int totalDeadRoosters = calculateDeadRoosters(collectChickens);
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviary.getCurrentAmountOfChickens();
        int currentRoosters = aviary.getCurrentAmountOfRoosters();
        int totalBirds = currentChickens + currentRoosters;
        double production = (double) totalEggsCollected / currentChickens;
        double roosterMortality = (double) totalDeadRoosters / currentRoosters;
        double chickenMortality = (double) totalDeadChickens / currentChickens;
        double mortality = (double) totalDeadBirds / totalBirds;
        double chickenRoosterProportion = (double) currentChickens / currentRoosters;

        List<EggDetailBO> quantityByEggType = calculateEggsByType(collectEggs);

        List<EggDetailPercentage> percentageByEggType = calculateEggPercentageByType(quantityByEggType, totalEggsCollected);

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
                quantityByEggType,
                percentageByEggType
        );
    }
}

