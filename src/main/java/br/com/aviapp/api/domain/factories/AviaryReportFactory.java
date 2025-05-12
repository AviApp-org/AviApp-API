package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AviaryReportFactory extends CollectCalculator{

    @Autowired
    private EggDetailMapperBO eggDetailMapperBO;

    public static AviaryReportBO createFromAviary(AviaryBO aviary, List<CollectEggBO> collectEggs, List<CollectChickenBO> collectChickens) {
        int totalEggsCollected = calculateTotalEggs(collectEggs);
        int totalDeadChickens = calculateDeadChickens(collectChickens);
        int totalDeadRoosters = calculateDeadRoosters(collectChickens);
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviary.getInitialAmountOfChickens() - totalDeadChickens;
        int currentRoosters = aviary.getInitialAmountOfRoosters() - totalDeadRoosters;
        int totalBirds = currentChickens + currentRoosters;

        return new AviaryReportBO(aviary.getName(), collectEggs, collectChickens, totalEggsCollected, totalDeadBirds, totalDeadChickens, totalDeadRoosters, currentChickens, currentRoosters, totalBirds);
    }

}
