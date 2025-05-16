package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.AviaryReportDTO;
import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.domain.entities.AviaryReportBO;
import br.com.aviapp.api.domain.entities.DailyReportBO;

import java.util.List;
import java.util.stream.Collectors;

public class DailyReportMapperBO {

    private final AviaryReportMapperBO aviaryReportMapperBO;

    public DailyReportMapperBO(AviaryReportMapperBO aviaryReportMapperBO) {
        this.aviaryReportMapperBO = aviaryReportMapperBO;
    }

    public DailyReportBO toBO(DailyReportDTO dto) {

        List<AviaryReportBO> aviaryReports =aviaryReportMapperBO.toBOList(dto.aviaryReports());

        return new DailyReportBO(
                dto.date(),
                aviaryReports,
                dto.totalEggsCollected(),
                dto.totalDeadBirds(),
                dto.totalDeadChickens(),
                dto.totalDeadRoosters(),
                dto.currentChickens(),
                dto.currentRoosters(),
                dto.totalBirds()
        );
    }

    public DailyReportDTO toDTO(DailyReportBO bo) {

        List<AviaryReportDTO> aviaryReportDTOs = aviaryReportMapperBO.toDTOList(bo.getAviaryReports());

        return new DailyReportDTO(
                bo.getDate(),
                aviaryReportDTOs,
                bo.getTotalEggsCollected(),
                bo.getTotalDeadBirds(),
                bo.getTotalDeadChickens(),
                bo.getTotalDeadRoosters(),
                bo.getCurrentChickens(),
                bo.getCurrentRoosters(),
                bo.getTotalBirds()
        );
    }

    public List<DailyReportBO> toBOList(List<DailyReportDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<DailyReportDTO> toDTOList(List<DailyReportBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
