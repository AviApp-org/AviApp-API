package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.domain.entities.*;
import br.com.aviapp.api.domain.enums.EnumEggType;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DailyReportFactory extends CollectCalculator {

    public static DailyReportBO createDailyReport(List<AviaryReportBO> aviaryReports, Date date) {
        int totalEggsCollected = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalEggsCollected).sum();
        int totalDeadChickens = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalDeadChickens).sum();
        int totalDeadRoosters = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalDeadRoosters).sum();
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviaryReports.stream().mapToInt(AviaryReportBO::getCurrentChickens).sum();
        int currentRoosters = aviaryReports.stream().mapToInt(AviaryReportBO::getCurrentRoosters).sum();

        int totalBirds = currentChickens + currentRoosters;

        float production = (float) totalEggsCollected / currentChickens;
        float roosterMortality= (float) totalDeadRoosters / currentRoosters;
        float chickenMortality = (float) totalDeadChickens / currentChickens;
        float mortality = (float) totalDeadBirds / totalBirds;
        float chickenRoosterProportion = (float) currentChickens / currentRoosters;

        List<EggDetailBO> quantityByEggType = calculateTotalEggsByType(aviaryReports);

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
                quantityByEggType
        );
    }

    private static List<EggDetailBO> calculateTotalEggsByType(List<AviaryReportBO> aviaryReports) {
        // Create a map to store the total quantity for each egg type
        Map<String, Integer> eggTypeQuantityMap = new HashMap<>();

        // Iterate through each aviary report
        for (AviaryReportBO aviaryReport : aviaryReports) {
            if (aviaryReport.getQuantityByEggType() != null) {
                // Add quantities from each aviary report to the map
                for (EggDetailBO eggDetail : aviaryReport.getQuantityByEggType()) {
                    String type = String.valueOf(eggDetail.getType());
                    int quantity = eggDetail.getQuantity();

                    // Update the map with the new quantity
                    eggTypeQuantityMap.put(type, eggTypeQuantityMap.getOrDefault(type, 0) + quantity);
                }
            }
        }

        // Convert the map back to a list of EggDetailBO
        return eggTypeQuantityMap.entrySet().stream()
                .map(entry -> new EggDetailBO(EnumEggType.valueOf(entry.getKey()), entry.getValue())).collect(Collectors.toList());
    }

}
