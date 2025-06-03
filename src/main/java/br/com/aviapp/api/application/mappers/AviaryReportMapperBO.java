package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.*;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class AviaryReportMapperBO {

    private final LookUpRepository lookUpRepository;
    private final CollectEggMapperBO collectEggMapperBO;
    private final CollectChickenMapperBO collectChickenMapperBO;
    private final EggDetailMapperBO eggDetailMapperBO;

    public AviaryReportMapperBO(LookUpRepository lookUpRepository, CollectEggMapperBO collectEggMapperBO, CollectChickenMapperBO collectChickenMapperBO, EggDetailMapperBO eggDetailMapperBO) {
        this.lookUpRepository = lookUpRepository;
        this.collectEggMapperBO = collectEggMapperBO;
        this.collectChickenMapperBO = collectChickenMapperBO;
        this.eggDetailMapperBO = eggDetailMapperBO;
    }

    public AviaryReportVO toBO(AviaryReportDTO dto) {
        List<CollectEggBO> collectEggs = collectEggMapperBO.toBOList(dto.eggCollects());
        List<CollectChickenBO> collectChickens = collectChickenMapperBO.toBOList(dto.chickenCollect());
        List<EggDetailBO> quantityByEggType = dto.quantityByEggType() != null ?
                eggDetailMapperBO.toBOList(dto.quantityByEggType()) :
                null;

        List<EggDetailPercentageVO> eggDetailPercentageVOS = dto.percentageByEggType() != null ?
                eggDetailMapperBO.toBOListPercentagem(dto.percentageByEggType()) :
                null;

        return new AviaryReportVO(
                dto.name(),
                collectEggs,
                collectChickens,
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

    public AviaryReportDTO toDTO(AviaryReportVO bo) {
        List<CollectEggDataDTO> eggCollects = collectEggMapperBO.toDTOList(bo.getCollectEggs());
        List<CollectChickenDTO> chickenCollect = collectChickenMapperBO.toDTOList(bo.getCollectChickens());
        List<EggDetailDTO> quantityByEggType = bo.getQuantityByEggType() != null ?
                eggDetailMapperBO.toDTOList(bo.getQuantityByEggType()) :
                null;

        List<EggDetailPercentageDTO> eggDetailPercentages = bo.getPercentageByEggType() != null ?
                eggDetailMapperBO.toPercentageDTOList(bo.getPercentageByEggType()) :
                null;

        return new AviaryReportDTO(
                bo.getName(),
                eggCollects,
                chickenCollect,
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

    public List<AviaryReportVO> toBOList(List<AviaryReportDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<AviaryReportDTO> toDTOList(List<AviaryReportVO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
