package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.entities.EggDetailBO;

public class CollectEggMapperBO {
    private final ILookUp lookUpRepository;
    private final AviaryMapperBO aviaryMapperBO;
    private final EggDetailMapperBO eggDetailMapperBO;

    public CollectEggMapperBO(ILookUp lookUpRepository, AviaryMapperBO aviaryMapperBO, EggDetailMapperBO eggDetailMapperBO) {
        this.lookUpRepository = lookUpRepository;
        this.aviaryMapperBO = aviaryMapperBO;
        this.eggDetailMapperBO = eggDetailMapperBO;
    }

    public CollectEggBO toBO(CollectEggDataDTO dto) {
        Optional<AviaryDTO> aviaryDTO = lookUpRepository.findAviaryDTOById(dto.aviaryId());
        if (aviaryDTO.isEmpty()) {
            throw new IllegalArgumentException("Aviário não encontrado.");
        }
        AviaryBO aviaryBO = aviaryMapperBO.toBO(aviaryDTO.get());
        List<EggDetailBO> eggDetails = eggDetailMapperBO.toBOList(dto.eggDetail());

        return new CollectEggBO(
                dto.id(),
                aviaryBO,
                eggDetails,
                dto.collectionDate()
        );
    }

    public CollectEggDataDTO toDTO(CollectEggBO bo) {

        List<EggDetailDTO> eggDetailDTOs = eggDetailMapperBO.toDTOList(bo.getEggDetails());
        return new CollectEggDataDTO(
                bo.getId(),
                bo.getAviary().getId(),
                eggDetailDTOs,
                bo.getCollectionDate()
        );
    }

    public List<CollectEggBO> toBOList(List<CollectEggDataDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<CollectEggDataDTO> toDTOList(List<CollectEggBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
