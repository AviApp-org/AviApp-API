package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.AviaryBO;

public class AviaryMapperBO {
    private final BatchMapperBO batchMapper;
    private final LookUpRepository lookUpRepository;

    public AviaryMapperBO(BatchMapperBO batchMapper, LookUpRepository lookUpRepository) {
        this.batchMapper = batchMapper;
        this.lookUpRepository = lookUpRepository;
    }

    public AviaryBO toBO(AviaryDTO dto) {
        Optional<BatchDTO> batch = lookUpRepository.findBatchDTOById(dto.batchId());
        return new AviaryBO(
            dto.id(),
            dto.name(),
            batchMapper.toBO(batch.get())
        );
    }

    public AviaryDTO toDTO(AviaryBO bo) {
        return new AviaryDTO(
            bo.getId(),
            bo.getName(),
            bo.getBatchId().getId()
        );
    }

    public List<AviaryBO> toBOList(List<AviaryDTO> dtos) {
        return dtos.stream()
            .map(this::toBO)
            .collect(Collectors.toList());
    }

    public List<AviaryDTO> toDTOList(List<AviaryBO> bos) {
        return bos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
