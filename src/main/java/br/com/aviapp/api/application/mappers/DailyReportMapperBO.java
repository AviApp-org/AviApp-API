package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.AviaryReportDTO;
import br.com.aviapp.api.application.dto.DailyReportDTO;
import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.application.dto.EggDetailPercentageDTO;
import br.com.aviapp.api.domain.entities.AviaryReportVO;
import br.com.aviapp.api.domain.entities.DailyReportVO;
import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.entities.EggDetailPercentageVO;

import java.util.List;
import java.util.stream.Collectors;

public class DailyReportMapperBO {

    private final AviaryReportMapperBO aviaryReportMapperBO;
    private final EggDetailMapperBO eggDetailMapperBO;

    public DailyReportMapperBO(AviaryReportMapperBO aviaryReportMapperBO, EggDetailMapperBO eggDetailMapperBO) {
        this.aviaryReportMapperBO = aviaryReportMapperBO;
        this.eggDetailMapperBO = eggDetailMapperBO;
    }

    public DailyReportVO toBO(DailyReportDTO dto) {

        List<AviaryReportVO> aviaryReports = aviaryReportMapperBO.toBOList(dto.aviaryReports());
        List<EggDetailBO> quantityByEggType = dto.quantityByEggType() != null ?
                eggDetailMapperBO.toBOList(dto.quantityByEggType()) :
                null;

        List<EggDetailPercentageVO> eggDetailPercentageVOS = dto.percentageByEggType() != null ?
                eggDetailMapperBO.toBOListPercentagem(dto.percentageByEggType()) :
                    null;

        return new DailyReportVO(
                dto.date(),
                aviaryReports,
                dto.totalEggsCollected(),
                dto.totalDeadBirds(),
                dto.totalDeadChickens(),
                dto.totalDeadRoosters(),
                dto.currentChickens(),
                dto.currentRoosters(),
                dto.totalBirds(),
                dto.production(),
                dto.roosterMortality(),
                dto.chickenMortality(),
                dto.mortality(),
                dto.chickenRoosterProportion(),
                quantityByEggType,
                eggDetailPercentageVOS,
                dto.marketEggs(),
                dto.dumpEggs(),
                dto.hatchableEggs()
        );
    }

    public DailyReportDTO toDTO(DailyReportVO bo) {

        List<AviaryReportDTO> aviaryReportDTOs = aviaryReportMapperBO.toDTOList(bo.getAviaryReports());
        List<EggDetailDTO> quantityByEggType = bo.getQuantityByEggType() != null ?
                eggDetailMapperBO.toDTOList(bo.getQuantityByEggType()) :
                null;

        List<EggDetailPercentageDTO> eggDetailPercentages = bo.getPercentageByEggType() != null ?
                eggDetailMapperBO.toPercentageDTOList(bo.getPercentageByEggType()) :
                null;

        return new DailyReportDTO(
                bo.getDate(),
                aviaryReportDTOs,
                bo.getTotalEggsCollected(),
                bo.getTotalDeadBirds(),
                bo.getTotalDeadChickens(),
                bo.getTotalDeadRoosters(),
                bo.getCurrentChickens(),
                bo.getCurrentRoosters(),
                bo.getTotalBirds(),
                bo.getProduction(),
                bo.getRoosterMortality(),
                bo.getChickenMortality(),
                bo.getMortality(),
                bo.getChickenRoosterProportion(),
                quantityByEggType,
                eggDetailPercentages,
                bo.getMarketEggs(),
                bo.getDumpEggs(),
                bo.getHatchableEggs()
        );
    }

    public List<DailyReportVO> toBOList(List<DailyReportDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<DailyReportDTO> toDTOList(List<DailyReportVO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
