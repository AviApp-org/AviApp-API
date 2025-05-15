package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class DailyReportFactory extends CollectCalculator {

    public static DailyReportBO createDailyReport(List<AviaryReportBO> aviaryReports ) {

        int totalEggsCollected = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalEggsCollected).sum();
        int totalDeadChickens = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalDeadChickens).sum();
        int totalDeadRoosters = aviaryReports.stream().mapToInt(AviaryReportBO::getTotalDeadRoosters).sum();
        int totalDeadBirds = totalDeadChickens + totalDeadRoosters;

        int currentChickens = aviaryReports.stream().mapToInt(AviaryReportBO::getCurrentChickens).sum();
        int currentRoosters = aviaryReports.stream().mapToInt(AviaryReportBO::getCurrentRoosters).sum();
        int totalBirds = currentChickens + currentRoosters;

        return new DailyReportBO(Date.from(Instant.now()), aviaryReports, totalEggsCollected, totalDeadBirds, totalDeadChickens, totalDeadRoosters, currentChickens, currentRoosters, totalBirds);


    }

}
