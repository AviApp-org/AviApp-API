package br.com.aviapp.api.application.dto;

import java.util.List;

public record AviaryReportDTO(String name, List<CollectEggDataDTO> eggCollects, List<CollectChickenDTO> chickenCollect,
                              int totalEggsCollected, int totalDeadBirds, int totalDeadChickens, int totalDeadRoosters,
                              int currentChickens, int currentRoosters, int totalBirds, float production,
                              float roosterMortality, float chickenMortality, float mortality,
                              float chickenRoosterProportion, List<EggDetailDTO> quantityByEggType) {
}
