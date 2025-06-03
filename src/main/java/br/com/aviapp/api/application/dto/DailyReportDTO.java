package br.com.aviapp.api.application.dto;

import java.time.LocalDate;
import java.util.List;

public record DailyReportDTO(LocalDate date, List<AviaryReportDTO> aviaryReports, int totalEggsCollected, int totalDeadBirds,
                             int totalDeadChickens, int totalDeadRoosters, int currentChickens, int currentRoosters,
                             int totalBirds, double production,
                             double roosterMortality, double chickenMortality, double mortality,
                             double chickenRoosterProportion, List<EggDetailDTO> quantityByEggType,
                             List<EggDetailPercentageDTO> percentageByEggType,
                             int marketEggs, int dumpEggs, int hatchableEggs) {
}

