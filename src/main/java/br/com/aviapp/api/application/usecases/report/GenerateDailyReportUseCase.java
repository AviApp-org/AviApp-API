package br.com.aviapp.api.application.usecases.report;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.AviaryReportBO;
import br.com.aviapp.api.domain.entities.DailyReportBO;
import br.com.aviapp.api.domain.factories.DailyReportFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateDailyReportUseCase {

    private final GenerateAviaryReportUseCase generateAviaryReportUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final AviaryMapperBO aviaryMapperBO;

    public GenerateDailyReportUseCase(GenerateAviaryReportUseCase generateAviaryReportUseCase, ListAviariesByBatchUseCase listAviariesByBatchUseCase, AviaryMapperBO aviaryMapperBO) {
        this.generateAviaryReportUseCase = generateAviaryReportUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.aviaryMapperBO = aviaryMapperBO;
    }

    public DailyReportBO invoke(Long batchId) {
        Optional<List<AviaryDTO>> aviaryDTO = listAviariesByBatchUseCase.invoke(batchId);
        List<AviaryBO> aviaryBO = aviaryMapperBO.toBOList(aviaryDTO.get());
        List<AviaryReportBO> aviaryReports = new ArrayList<>();

        for (AviaryBO aviary : aviaryBO) {
          aviaryReports.add(generateAviaryReportUseCase.invoke(aviary.getId(), LocalDateTime.now()))   ;
        }

        return DailyReportFactory.createDailyReport(aviaryReports);
    }
}
