package br.com.aviapp.api.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AviaryReportBO {

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

    public AviaryReportBO(String name, List<CollectEggBO> collectEggs, List<CollectChickenBO> collectChickens, int totalEggsCollected,
                          int totalDeadBirds, int totalDeadChickens, int totalDeadRoosters, int currentChickens, int currentRoosters, int totalBirds) {
        this.name = name;
        this.collectEggs = collectEggs;
        this.collectChickens = collectChickens;
        this.totalEggsCollected = totalEggsCollected;
        this.totalDeadBirds = totalDeadBirds;
        this.totalDeadChickens = totalDeadChickens;
        this.totalDeadRoosters = totalDeadRoosters;
        this.currentChickens = currentChickens;
        this.currentRoosters = currentRoosters;
        this.totalBirds = totalBirds;
    }


}
