package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.entities.AviaryReportBO;

import java.util.Date;
import java.util.List;

public record DailyReportDTO(Date date, List<AviaryReportDTO> aviaryReports, int totalEggsCollected, int totalDeadBirds,
                             int totalDeadChickens, int totalDeadRoosters, int currentChickens, int currentRoosters,
                             int totalBirds, List<EggDetailDTO> quantityByEggType) {
}
