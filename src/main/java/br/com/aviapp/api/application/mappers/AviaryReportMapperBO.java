package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.AviaryReportDTO;
import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.AviaryReportBO;
import br.com.aviapp.api.domain.entities.CollectChickenBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.entities.EggDetailBO;

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

    public AviaryReportBO toBO(AviaryReportDTO dto) {
        List<CollectEggBO> collectEggs = collectEggMapperBO.toBOList(dto.eggCollects());
        List<CollectChickenBO> collectChickens = collectChickenMapperBO.toBOList(dto.chickenCollect());
        List<EggDetailBO> quantityByEggType = dto.quantityByEggType() != null ?
                eggDetailMapperBO.toBOList(dto.quantityByEggType()) :
                null;

        return new AviaryReportBO(
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
                quantityByEggType
        );
    }

    public AviaryReportDTO toDTO(AviaryReportBO bo) {
        List<CollectEggDataDTO> eggCollects = collectEggMapperBO.toDTOList(bo.getCollectEggs());
        List<CollectChickenDTO> chickenCollect = collectChickenMapperBO.toDTOList(bo.getCollectChickens());
        List<EggDetailDTO> quantityByEggType = bo.getQuantityByEggType() != null ?
                eggDetailMapperBO.toDTOList(bo.getQuantityByEggType()) :
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
                quantityByEggType
        );
    }

    public List<AviaryReportBO> toBOList(List<AviaryReportDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<AviaryReportDTO> toDTOList(List<AviaryReportBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
