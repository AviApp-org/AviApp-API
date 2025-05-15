package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.application.mappers.DailyReportMapperBO;
import br.com.aviapp.api.application.usecases.report.GenerateDailyReportUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/daily-report")
@CrossOrigin("*")
public class DailyReportController {

    private final GenerateDailyReportUseCase generateDailyReportUseCase;
    private final DailyReportMapperBO dailyReportMapperBO;

    public DailyReportController(GenerateDailyReportUseCase generateDailyReportUseCase, DailyReportMapperBO dailyReportMapperBO) {
        this.generateDailyReportUseCase = generateDailyReportUseCase;
        this.dailyReportMapperBO = dailyReportMapperBO;
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<DailyReportDTO> generateDailyReport(@PathVariable Long batchId) {
        System.out.println("Received batchId: " + batchId);
        DailyReportDTO dailyReport = dailyReportMapperBO.toDTO(generateDailyReportUseCase.invoke(batchId));
        return ResponseEntity.ok(dailyReport);
    }
}
