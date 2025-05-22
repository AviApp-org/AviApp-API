package br.com.aviapp.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportBO {

    private Date date;
    private List<AviaryReportBO> aviaryReports;
    private int totalEggsCollected;
    private int totalDeadBirds;
    private int totalDeadChickens;
    private int totalDeadRoosters;
    private int currentChickens;
    private int currentRoosters;
    private int totalBirds;
    private BigDecimal production;
    private BigDecimal roosterMortality;
    private BigDecimal chickenMortality;
    private BigDecimal mortality;
    private BigDecimal chickenRoosterProportion;
    private List<EggDetailBO> quantityByEggType;


}
