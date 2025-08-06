package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.application.dto.MonthlyReportDTO;
import br.com.aviapp.api.application.dto.WeeklyReportDTO;
import br.com.aviapp.api.application.usecases.report.GenerateDailyReportUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;


@RestController
@RequestMapping("/api/daily-report")
@CrossOrigin("*")
public class DailyReportController {

    private final GenerateDailyReportUseCase generateDailyReportUseCase;

    public DailyReportController(GenerateDailyReportUseCase generateDailyReportUseCase) {
        this.generateDailyReportUseCase = generateDailyReportUseCase;
    }

    @GetMapping("/{batchId}/{localDate}")
    public ResponseEntity<DailyReportDTO> generateDailyReport(@PathVariable Long batchId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate localDate) {
        DailyReportDTO dailyReport = generateDailyReportUseCase.invoke(batchId, localDate);
        return ResponseEntity.ok(dailyReport);
    }

    @GetMapping("/week/{batchId}/{localDate}")
    public ResponseEntity<WeeklyReportDTO> generateWeeklyReport(@PathVariable Long batchId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate localDate) {
        WeeklyReportDTO weeklyReport = generateDailyReportUseCase.generateWeeklyReport(batchId, localDate);
        return ResponseEntity.ok(weeklyReport);
    }

    @GetMapping("/month/{batchId}/{yearMonth}")
    public ResponseEntity<MonthlyReportDTO> generateMonthlyReport(@PathVariable Long batchId, @PathVariable @DateTimeFormat(pattern = "MM-yyyy") YearMonth yearMonth) {
        return ResponseEntity.ok(generateDailyReportUseCase.generateMonthlyReport(batchId, yearMonth));
    }

}
