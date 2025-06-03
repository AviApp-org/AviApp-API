package br.com.aviapp.api.application.dto;

import java.time.LocalDate;
import java.util.List;

public record MonthlyReportDTO (String batch, LocalDate startDate, LocalDate endDate, List<DailyReportDTO> dailyReports) {
}
