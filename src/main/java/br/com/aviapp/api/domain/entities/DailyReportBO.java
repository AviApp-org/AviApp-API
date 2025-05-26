package br.com.aviapp.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportBO {

    private LocalDate date;
    private List<AviaryReportBO> aviaryReports;
    private int totalEggsCollected;
    private int totalDeadBirds;
    private int totalDeadChickens;
    private int totalDeadRoosters;
    private int currentChickens;
    private int currentRoosters;
    private int totalBirds;
    private double production;
    private double roosterMortality;
    private double chickenMortality;
    private double mortality;
    private double chickenRoosterProportion;
    private List<EggDetailBO> quantityByEggType;
    private List<EggDetailPercentageVO> percentageByEggType;
    private int marketEggs;
    private int dumpEggs;
    private int incubateEggs;

}
