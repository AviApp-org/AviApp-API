package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.application.dto.FinancialDetailsDTO;
import br.com.aviapp.api.application.usecases.financial.GenerateFinancialReportUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static java.sql.DriverManager.println;

@RestController
@RequestMapping("/api/financial-details")
@CrossOrigin("*")
public class FinancialDetailController {

    private final GenerateFinancialReportUseCase generateFinancialReportUseCase;


    public FinancialDetailController(GenerateFinancialReportUseCase generateFinancialReportUseCase) {
        this.generateFinancialReportUseCase = generateFinancialReportUseCase;
    }

    @GetMapping("/{batchId}/{localDate}")
    public ResponseEntity<FinancialDetailsDTO> generateFinancialDetail(@PathVariable Long batchId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate localDate) throws Exception {
        FinancialDetailsDTO financialDetails = generateFinancialReportUseCase.getDailyFinancialReport(localDate, batchId);
        return ResponseEntity.ok(financialDetails);
    }

    @GetMapping("/{batchId}/weekly/{startDate}")
    public ResponseEntity<FinancialDetailsDTO> generateWeeklyFinancialDetail(@PathVariable Long batchId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate) throws Exception {
        FinancialDetailsDTO financialDetails = generateFinancialReportUseCase.getWeeklyFinancialReport(startDate, batchId);
        return ResponseEntity.ok(financialDetails);
    }

    @GetMapping("/{batchId}/monthly/{yearMonth}")
    public ResponseEntity<FinancialDetailsDTO> generateMonthlyFinancialDetail(@PathVariable Long batchId, @PathVariable @DateTimeFormat(pattern = "MM-yyyy") YearMonth yearMonth) throws Exception {
        FinancialDetailsDTO financialDetails = generateFinancialReportUseCase.getMonthlyFinancialReport(yearMonth, batchId);
        return ResponseEntity.ok(financialDetails);
    }
}
