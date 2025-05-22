package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.application.dto.WeeklyReportDTO;
import br.com.aviapp.api.application.mappers.DailyReportMapperBO;
import br.com.aviapp.api.application.usecases.report.GenerateDailyReportUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/daily-report")
@CrossOrigin("*")
public class DailyReportController {

    private final GenerateDailyReportUseCase generateDailyReportUseCase;

    public DailyReportController(GenerateDailyReportUseCase generateDailyReportUseCase) {
        this.generateDailyReportUseCase = generateDailyReportUseCase;
    }

    @GetMapping("/{batchId}/{localDate}")
    public ResponseEntity<DailyReportDTO> generateDailyReport(@PathVariable Long batchId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        DailyReportDTO dailyReport = generateDailyReportUseCase.invoke(batchId, date);
        return ResponseEntity.ok(dailyReport);
    }

    @GetMapping("/week/{batchId}/{localDate}")
    public ResponseEntity<WeeklyReportDTO> generateWeeklyReport(@PathVariable Long batchId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        WeeklyReportDTO weeklyReport = generateDailyReportUseCase.generateWeeklyReport(batchId, date);
        return ResponseEntity.ok(weeklyReport);
    }
}
