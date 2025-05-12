package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DailyReportFactory extends  CollectCalculator {

    @Autowired
    private EggDetailMapperBO eggDetailMapperBO;

    public DailyReportFactory createDailyReport(DailyReportBO dailyReportBO, List<AviaryReportBO> aviaryReports, List<CollectEggBO> collectEggs, List<CollectChickenBO> collectChickens, AviaryBO aviary) {

        int totalEggsCollected = calculateTotalEggs(collectEggs);
        int totalDeadChickens = calculateDeadChickens(collectChickens);
        int totalDeadRoosters = calculateDeadRoosters(collectChickens);
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviary.getCurrentAmountOfChickens();
        int currentRoosters = aviary.getCurrentAmountOfRoosters();
        int totalBirds = currentChickens + currentRoosters;

        return null;


    }

}
