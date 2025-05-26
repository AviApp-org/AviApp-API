package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.*;
import br.com.aviapp.api.domain.enums.EnumEggType;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DailyReportFactory extends CollectCalculator {

    public static DailyReportBO createDailyReport(List<AviaryReportBO> aviaryReports, LocalDate date) {
        int totalEggsCollected = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalEggsCollected).sum();
        int totalDeadChickens = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalDeadChickens).sum();
        int totalDeadRoosters = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalDeadRoosters).sum();
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviaryReports.stream().mapToInt(AviaryReportBO::getCurrentChickens).sum();
        int currentRoosters = aviaryReports.stream().mapToInt(AviaryReportBO::getCurrentRoosters).sum();

        int totalBirds = currentChickens + currentRoosters;

        double production = (double) totalEggsCollected / currentChickens;
        double roosterMortality = (double) totalDeadRoosters / currentRoosters;
        double chickenMortality = (double) totalDeadChickens / currentChickens;
        double mortality = (double) totalDeadBirds / totalBirds;
        double chickenRoosterProportion = (double) currentChickens / currentRoosters;

        List<EggDetailBO> quantityByEggType = calculateTotalEggsByType(aviaryReports);

        int marketEggs = calculateMarketEggsByType(quantityByEggType);
        int dumpEggs = calculateDumpEggsByType(quantityByEggType);
        int incubateEggs = calculateIncubateEggs(quantityByEggType);

        List<EggDetailPercentageVO> percentageByEggType = calculateEggPercentageByType(quantityByEggType, totalEggsCollected);

        return new DailyReportBO(
                date,
                aviaryReports,
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
                percentageByEggType,
                marketEggs,
                dumpEggs,
                incubateEggs
        );
    }

    private static List<EggDetailBO> calculateTotalEggsByType(List<AviaryReportBO> aviaryReports) {
        Map<String, Integer> eggTypeQuantityMap = new HashMap<>();

        for (AviaryReportBO aviaryReport : aviaryReports) {
            if (aviaryReport.getQuantityByEggType() != null) {
                for (EggDetailBO eggDetail : aviaryReport.getQuantityByEggType()) {
                    String type = String.valueOf(eggDetail.getType());
                    int quantity = eggDetail.getQuantity();

                    eggTypeQuantityMap.put(type, eggTypeQuantityMap.getOrDefault(type, 0) + quantity);
                }
            }
        }

        return eggTypeQuantityMap.entrySet().stream()
                .map(entry -> new EggDetailBO(EnumEggType.valueOf(entry.getKey()), entry.getValue())).collect(Collectors.toList());
    }


}