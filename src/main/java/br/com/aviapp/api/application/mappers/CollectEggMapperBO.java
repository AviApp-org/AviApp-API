package br.com.aviapp.api.application.mappers;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;

public class CollectEggMapperBO {
    private final LookUpRepository lookUpRepository;
    private final AviaryMapperBO aviaryMapperBO;

    public CollectEggMapperBO(LookUpRepository lookUpRepository, AviaryMapperBO aviaryMapperBO) {
        this.lookUpRepository = lookUpRepository;
        this.aviaryMapperBO = aviaryMapperBO;
    }

    public CollectEggBO toBO(CollectEggDataDTO dto) {
        Optional<AviaryDTO> aviaryDTO = lookUpRepository.findAviaryDTOById(dto.aviaryId());
        if (aviaryDTO.isEmpty()) {
            throw new IllegalArgumentException("Aviário não encontrado.");
        }
        AviaryBO aviaryBO = aviaryMapperBO.toBO(aviaryDTO.get());
        return new CollectEggBO(
            dto.id(),
            aviaryBO,
            dto.eggDetailBOS(),
            LocalDateTime.now()
        );
    }

    public CollectEggDataDTO toDTO(CollectEggBO bo) {
        return new CollectEggDataDTO(
            bo.getId(),
            bo.getAviary().getId(),
            bo.getEggDetails().stream().toList(),
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
