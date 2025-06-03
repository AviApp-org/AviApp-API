package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.*;
import br.com.aviapp.api.domain.enums.EnumEggType;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DailyReportFactory extends CollectCalculator {

    public static DailyReportVO createDailyReport(List<AviaryReportVO> aviaryReports, LocalDate date) {
        int totalEggsCollected = aviaryReports.stream().mapToInt(AviaryReportVO::getTotalEggsCollected).sum();
        int totalDeadChickens = aviaryReports.stream().mapToInt(AviaryReportVO::getTotalDeadChickens).sum();
        int totalDeadRoosters = aviaryReports.stream().mapToInt(AviaryReportVO::getTotalDeadRoosters).sum();
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviaryReports.stream().mapToInt(AviaryReportVO::getCurrentChickens).sum();
        int currentRoosters = aviaryReports.stream().mapToInt(AviaryReportVO::getCurrentRoosters).sum();

        int totalBirds = currentChickens + currentRoosters;

        double production = (double) totalEggsCollected / currentChickens;
        double roosterMortality = (double) totalDeadRoosters / currentRoosters;
        double chickenMortality = (double) totalDeadChickens / currentChickens;
        double mortality = (double) totalDeadBirds / totalBirds;
        double chickenRoosterProportion = (double) currentChickens / currentRoosters;

        List<EggDetailBO> quantityByEggType = calculateTotalEggsByType(aviaryReports);

        int marketEggs = calculateMarketEggs(quantityByEggType);
        int dumpEggs = calculateDumpEggs(quantityByEggType);
        int incubateEggs = calculateHatchableEggs(quantityByEggType);

        List<EggDetailPercentageVO> percentageByEggType = calculateEggPercentageByType(quantityByEggType, totalEggsCollected);

        return new DailyReportVO(
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

    private static List<EggDetailBO> calculateTotalEggsByType(List<AviaryReportVO> aviaryReports) {
        Map<String, Integer> eggTypeQuantityMap = new HashMap<>();

        for (AviaryReportVO aviaryReport : aviaryReports) {
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