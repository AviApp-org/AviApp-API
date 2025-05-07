package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.CollectEggBO;

public class CollectEggMapperBO {
    private final LookUpRepository lookUpRepository;
    private final CollectMapperBO collectMapper;

    public CollectEggMapperBO(LookUpRepository lookUpRepository, CollectMapperBO collectMapper) {
        this.lookUpRepository = lookUpRepository;
        this.collectMapper = collectMapper;
    }

    public CollectEggBO toBO(CollectEggDataDTO dto) {
        Optional<CollectDTO> collect = lookUpRepository.findCollectById(dto.collectId());
        if (collect.isEmpty()) {
            throw new IllegalArgumentException("Collect not found");
        }
        CollectBO collectBO = collectMapper.toBO(collect.get());
        return new CollectEggBO(
            dto.id(),
            collectBO,
            dto.egg(),
            dto.quantity()
        );
    }

    public CollectEggDataDTO toDTO(CollectEggBO bo) {
        return new CollectEggDataDTO(
            bo.getId(),
            bo.getCollect().getId(),
            bo.getEgg(),
            bo.getQuantity()
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
