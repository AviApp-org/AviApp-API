package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.BatchBO;

public class BatchMapperBO {
    private final FarmMapperBO farmMapper;
    private final LookUpRepository lookUpRepository;

    public BatchMapperBO(FarmMapperBO farmMapper, LookUpRepository lookUpRepository) {
        this.farmMapper = farmMapper;
        this.lookUpRepository = lookUpRepository;
    }

    public BatchBO toBO(BatchDTO dto) {
        Optional<FarmDTO> farm = lookUpRepository.findFarmDTOById(dto.farmId());
        return new BatchBO(
            dto.id(),
            farmMapper.toBO(farm.get())
        );
    }

    public BatchDTO toDTO(BatchBO bo) {
        return new BatchDTO(
            bo.getId(),
            bo.getFarm().getId()
        );
    }

    public List<BatchBO> toBOList(List<BatchDTO> dtos) {
        return dtos.stream()
            .map(this::toBO)
            .collect(Collectors.toList());
    }

    public List<BatchDTO> toDTOList(List<BatchBO> bos) {
        return bos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
