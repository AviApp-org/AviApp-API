package br.com.aviapp.api.application.dto;

import java.math.BigDecimal;
import java.util.List;

public record AviaryReportDTO(String name, List<CollectEggDataDTO> eggCollects, List<CollectChickenDTO> chickenCollect,
                              int totalEggsCollected, int totalDeadBirds, int totalDeadChickens, int totalDeadRoosters,
                              int currentChickens, int currentRoosters, int totalBirds, BigDecimal production,
                              BigDecimal roosterMortality, BigDecimal chickenMortality, BigDecimal mortality,
                              BigDecimal chickenRoosterProportion, List<EggDetailDTO> quantityByEggType) {
}
