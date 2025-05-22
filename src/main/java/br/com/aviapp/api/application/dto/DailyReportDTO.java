package br.com.aviapp.api.application.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record DailyReportDTO(Date date, List<AviaryReportDTO> aviaryReports, int totalEggsCollected, int totalDeadBirds,
                             int totalDeadChickens, int totalDeadRoosters, int currentChickens, int currentRoosters,
                             int totalBirds, BigDecimal production,
                             BigDecimal roosterMortality, BigDecimal chickenMortality, BigDecimal mortality,
                             BigDecimal chickenRoosterProportion, List<EggDetailDTO> quantityByEggType) {
}

