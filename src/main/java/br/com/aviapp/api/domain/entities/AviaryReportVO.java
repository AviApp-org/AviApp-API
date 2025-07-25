package br.com.aviapp.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AviaryReportVO {

    private String name;
    private List<CollectEggBO> collectEggs;
    private List<CollectChickenBO> collectChickens;
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
    private List<EggDetailBO> quantityByEggType; // New field for egg details by type
    private List<EggDetailPercentageVO> percentageByEggType;
    private int marketEggs;
    private int dumpEggs;
    private int hatchableEggs;

}
