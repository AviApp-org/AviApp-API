package br.com.aviapp.api.application.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record WeeklyReportDTO (String batch, LocalDate startDate, LocalDate endDate, List<DailyReportDTO> dailyReports) {
}
