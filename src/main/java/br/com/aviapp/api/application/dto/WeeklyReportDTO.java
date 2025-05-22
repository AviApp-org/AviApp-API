package br.com.aviapp.api.application.dto;

import java.util.Date;
import java.util.List;

public record WeeklyReportDTO (String batch, Date startDate, Date endDate, List<DailyReportDTO> dailyReports) {
}
