package br.com.aviapp.api.application.usecases.report;

import br.com.aviapp.api.application.dto.*;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.AviaryReportMapperBO;
import br.com.aviapp.api.application.mappers.DailyReportMapperBO;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.batch.FindBatchByIdUseCase;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.AviaryReportBO;
import br.com.aviapp.api.domain.entities.DailyReportBO;
import br.com.aviapp.api.domain.factories.DailyReportFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GenerateDailyReportUseCase {

    private final GenerateAviaryReportUseCase generateAviaryReportUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final AviaryMapperBO aviaryMapperBO;
    private final DailyReportMapperBO dailyReportMapperBO;
    private final AviaryReportMapperBO  aviaryReportMapperBO;
    private final FindBatchByIdUseCase findBatchByIdUseCase;

    public GenerateDailyReportUseCase(GenerateAviaryReportUseCase generateAviaryReportUseCase, ListAviariesByBatchUseCase listAviariesByBatchUseCase, AviaryMapperBO aviaryMapperBO, DailyReportMapperBO dailyReportMapperBO, AviaryReportMapperBO aviaryReportMapperBO, FindBatchByIdUseCase findBatchByIdUseCase) {
        this.generateAviaryReportUseCase = generateAviaryReportUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.aviaryMapperBO = aviaryMapperBO;
        this.dailyReportMapperBO = dailyReportMapperBO;
        this.aviaryReportMapperBO = aviaryReportMapperBO;
        this.findBatchByIdUseCase = findBatchByIdUseCase;
    }

    public DailyReportDTO invoke(Long batchId, Date date) {
        Optional<List<AviaryDTO>> aviaryDTO = listAviariesByBatchUseCase.invoke(batchId);
        List<AviaryBO> aviaryBO = aviaryMapperBO.toBOList(aviaryDTO.get());
        List<AviaryReportDTO> aviaryReports = new ArrayList<>();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        for (AviaryBO aviary : aviaryBO) {
          aviaryReports.add(generateAviaryReportUseCase.invoke(aviary.getId(), localDateTime));
        }

        List<AviaryReportBO> aviaryReportBO = aviaryReportMapperBO.toBOList(aviaryReports);

        DailyReportBO dailyReportBO = DailyReportFactory.createDailyReport(aviaryReportBO, date);

        return dailyReportMapperBO.toDTO(dailyReportBO);
    }

    public WeeklyReportDTO generateWeeklyReport(Long batchId, Date startDate) {
        List<DailyReportDTO> dailyReports = new ArrayList<>();
        Optional<BatchDTO> batchDTO = findBatchByIdUseCase.invoke(batchId);

        LocalDateTime currentDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = currentDate.plusDays(6);

        for (int i = 0; i < 7; i++) {
            Date reportDate = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());

            DailyReportDTO dailyReport = invoke(batchId, reportDate);

            dailyReports.add(dailyReport);

            currentDate = currentDate.plusDays(1);
        }

        Date start = Date.from(startDate.toInstant());
        Date end = Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant());

        return new WeeklyReportDTO(batchDTO.get().name(), start, end, dailyReports);
    }


}
