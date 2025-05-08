package br.com.aviapp.api.application.mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.CollectChickenBO;

public class CollectChickenMapperBO {
    private final LookUpRepository lookUpRepository;
    private final AviaryMapperBO aviaryMapperBO;

    public CollectChickenMapperBO(LookUpRepository lookUpRepository, AviaryMapperBO aviaryMapperBO) {
        this.lookUpRepository = lookUpRepository;
        this.aviaryMapperBO = aviaryMapperBO;
    }

    public CollectChickenBO toBO(CollectChickenDTO dto) {
        Optional<AviaryDTO> collect = lookUpRepository.findAviaryDTOById(dto.aviaryId());
        if (collect.isEmpty()) {
            throw new IllegalArgumentException("Collect not found");
        }
        AviaryBO aviaryBO = aviaryMapperBO.toBO(collect.get());
        return new CollectChickenBO(
            dto.id(),
            aviaryBO,
            dto.deadRoosters(),
            dto.deadChickens(),
            dto.observation(),
            LocalDateTime.now()

        );
    }

    public CollectChickenDTO toDTO(CollectChickenBO bo) {
        return new CollectChickenDTO(
            bo.getId(),
            bo.getAviary().getId(),
            bo.getDeadRoosters(),
            bo.getDeadChickens(),
            bo.getObservation(),
            bo.getCollectionDate()
        );
    }

    public List<CollectChickenBO> toBOList(List<CollectChickenDTO> dtos) {
        return dtos.stream()
            .map(this::toBO)
            .collect(Collectors.toList());
    }

    public List<CollectChickenDTO> toDTOList(List<CollectChickenBO> bos) {
        return bos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
