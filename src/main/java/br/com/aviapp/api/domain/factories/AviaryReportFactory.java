package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

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
        float production = (float) totalEggsCollected / currentChickens;
        float roosterMortality= (float) totalDeadRoosters / currentRoosters;
        float chickenMortality = (float) totalDeadChickens / currentChickens;
        float mortality = (float) totalDeadBirds / totalBirds;
        float chickenRoosterProportion = (float) currentChickens / currentRoosters;

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

