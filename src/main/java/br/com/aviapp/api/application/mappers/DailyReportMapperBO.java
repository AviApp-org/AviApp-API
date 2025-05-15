package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.domain.entities.DailyReportBO;

import java.util.List;
import java.util.stream.Collectors;

public class DailyReportMapperBO {
    public DailyReportBO toBO(DailyReportDTO dto) {
        return new DailyReportBO(
                dto.date(),
                dto.aviaryReports(),
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
        return new DailyReportDTO(
                bo.getDate(),
                bo.getAviaryReports(),
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
