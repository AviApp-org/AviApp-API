package br.com.aviapp.api.application.dto;

import java.util.List;

public record AviaryReportDTO(String name, List<CollectEggDataDTO> eggCollects, List<CollectChickenDTO> chickenCollect,
                              int totalEggsCollected, int totalDeadBirds, int totalDeadChickens, int totalDeadRoosters,
                              int currentChickens, int currentRoosters, int totalBirds, double production,
                              double roosterMortality, double chickenMortality, double mortality,
                              double chickenRoosterProportion, List<EggDetailDTO> quantityByEggType, List<EggDetailPercentageDTO> percentageByEggType) {
}
