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
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateDailyReportUseCase {

    private final GenerateAviaryReportUseCase generateAviaryReportUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final AviaryMapperBO aviaryMapperBO;
    private final DailyReportMapperBO dailyReportMapperBO;
    private final AviaryReportMapperBO aviaryReportMapperBO;
    private final FindBatchByIdUseCase findBatchByIdUseCase;

    public GenerateDailyReportUseCase(
            GenerateAviaryReportUseCase generateAviaryReportUseCase,
            ListAviariesByBatchUseCase listAviariesByBatchUseCase,
            AviaryMapperBO aviaryMapperBO,
            DailyReportMapperBO dailyReportMapperBO,
            AviaryReportMapperBO aviaryReportMapperBO,
            FindBatchByIdUseCase findBatchByIdUseCase
    ) {
        this.generateAviaryReportUseCase = generateAviaryReportUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.aviaryMapperBO = aviaryMapperBO;
        this.dailyReportMapperBO = dailyReportMapperBO;
        this.aviaryReportMapperBO = aviaryReportMapperBO;
        this.findBatchByIdUseCase = findBatchByIdUseCase;
    }

    public DailyReportDTO invoke(Long batchId, LocalDate date) {
        List<AviaryDTO> aviaryDTOs = listAviariesByBatchUseCase.invoke(batchId);
        if (aviaryDTOs.isEmpty()) {
            throw new RuntimeException("Não existem aviários cadastrados para esse lote");
        }

        List<AviaryBO> aviaryBOs = aviaryMapperBO.toBOList(aviaryDTOs);

        List<AviaryReportDTO> aviaryReports = aviaryBOs.stream()
                .map(aviary -> generateAviaryReportUseCase.invoke(aviary.getId(), date))
                .toList();

        List<AviaryReportVO> aviaryReportVOs = aviaryReportMapperBO.toBOList(aviaryReports);
        DailyReportVO dailyReportVO = DailyReportFactory.createDailyReport(aviaryReportVOs, date);

        return dailyReportMapperBO.toDTO(dailyReportVO);
    }

    public WeeklyReportDTO generateWeeklyReport(Long batchId, LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(6);
        List<LocalDate> dates = generateDateRange(startDate, endDate);
        return new WeeklyReportDTO(getBatchNameOrThrow(batchId), startDate, endDate, generateDailyReports(batchId, dates));
    }

    public MonthlyReportDTO generateMonthlyReport(Long batchId, YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<LocalDate> dates = generateDateRange(startDate, endDate);
        List<DailyReportDTO> dailyReports = generateDailyReports(batchId, dates);

        return new MonthlyReportDTO(getBatchNameOrThrow(batchId), startDate, endDate, dailyReports);
    }

    private List<DailyReportDTO> generateDailyReports(Long batchId, List<LocalDate> dates) {
        return dates.stream()
                .map(date -> invoke(batchId, date))
                .toList();
    }

    private List<LocalDate> generateDateRange(LocalDate start, LocalDate endInclusive) {
        return start.datesUntil(endInclusive.plusDays(1)).toList(); // inclusive
    }

    private String getBatchNameOrThrow(Long batchId) {
        return findBatchByIdUseCase.invoke(batchId)
                .map(BatchDTO::name)
                .orElseThrow(() -> new RuntimeException("Batch not found"));
    }

}
