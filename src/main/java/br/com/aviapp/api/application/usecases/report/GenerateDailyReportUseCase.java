package br.com.aviapp.api.application.usecases.report;

import br.com.aviapp.api.application.dto.*;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.AviaryReportMapperBO;
import br.com.aviapp.api.application.mappers.DailyReportMapperBO;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.batch.FindBatchByIdUseCase;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.AviaryReportVO;
import br.com.aviapp.api.domain.entities.DailyReportVO;
import br.com.aviapp.api.domain.factories.DailyReportFactory;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public DailyReportDTO invoke(Long batchId, LocalDate date) {
        Optional<List<AviaryDTO>> aviaryDTO = listAviariesByBatchUseCase.invoke(batchId);
        List<AviaryBO> aviaryBO = aviaryMapperBO.toBOList(aviaryDTO.get());
        List<AviaryReportDTO> aviaryReports = new ArrayList<>();

        for (AviaryBO aviary : aviaryBO) {
          aviaryReports.add(generateAviaryReportUseCase.invoke(aviary.getId(), date));
        }

        List<AviaryReportVO> aviaryReportVO = aviaryReportMapperBO.toBOList(aviaryReports);

        DailyReportVO dailyReportVO = DailyReportFactory.createDailyReport(aviaryReportVO, date);

        return dailyReportMapperBO.toDTO(dailyReportVO);
    }

    public WeeklyReportDTO generateWeeklyReport(Long batchId, LocalDate startDate) {
        List<DailyReportDTO> dailyReports = new ArrayList<>();
        Optional<BatchDTO> batchDTO = findBatchByIdUseCase.invoke(batchId);

        LocalDate endDate = startDate.plusDays(6);

        for (int i = 0; i < 7; i++) {

            DailyReportDTO dailyReport = invoke(batchId, startDate);

            dailyReports.add(dailyReport);

            startDate = startDate.plusDays(1);
        }


        return new WeeklyReportDTO(batchDTO.get().name(), startDate, endDate, dailyReports);
    }

    public MonthlyReportDTO generateMonthlyReport(Long batchId, LocalDate startDate) {
        List<DailyReportDTO> dailyReports = new ArrayList<>();
        Optional<BatchDTO> batchDTO = findBatchByIdUseCase.invoke(batchId);

        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        while (startDate.isBefore(endDate)) {

            DailyReportDTO dailyReport = invoke(batchId, startDate);

            dailyReports.add(dailyReport);

            startDate = startDate.plusDays(1);

        }
        return new MonthlyReportDTO(batchDTO.get().name(), startDate, endDate, dailyReports);
    }

}
