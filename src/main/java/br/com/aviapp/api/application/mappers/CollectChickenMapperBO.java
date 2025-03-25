package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.CollectChickenBO;
import br.com.aviapp.api.domain.entities.CollectBO;

public class CollectChickenMapperBO {
    private final LookUpRepository lookUpRepository;
    private final CollectMapperBO collectMapper;

    public CollectChickenMapperBO(LookUpRepository lookUpRepository, CollectMapperBO collectMapper) {
        this.lookUpRepository = lookUpRepository;
        this.collectMapper = collectMapper;
    }

    public CollectChickenBO toBO(CollectChickenDTO dto) {
        Optional<CollectDTO> collect = lookUpRepository.findCollectById(dto.collectId());
        if (collect.isEmpty()) {
            throw new IllegalArgumentException("Collect not found");
        }
        CollectBO collectBO = collectMapper.toBO(collect.get());
        return new CollectChickenBO(
            dto.id(),
            collectBO,
            dto.deadRoosters(),
            dto.deadChickens(),
            dto.deathCause(),
            dto.observation()
        );
    }

    public CollectChickenDTO toDTO(CollectChickenBO bo) {
        return new CollectChickenDTO(
            bo.getId(),
            bo.getCollect().getId(),
            bo.getDeadRoosters(),
            bo.getDeadChickens(),
            bo.getDeathCause(),
            bo.getObservation()
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
