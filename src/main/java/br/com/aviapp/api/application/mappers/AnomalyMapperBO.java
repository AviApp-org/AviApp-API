package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import br.com.aviapp.api.domain.entities.AnomalyBO;
import br.com.aviapp.api.domain.entities.AviaryBO;

public class AnomalyMapperBO {

    private final FindAviaryByIdUseCase findAviaryByIdUseCase;
    private final AviaryMapperBO aviaryMapperBO;

    public AnomalyMapperBO(FindAviaryByIdUseCase findAviaryByIdUseCase, AviaryMapperBO aviaryMapperBO) {
        this.findAviaryByIdUseCase = findAviaryByIdUseCase;
        this.aviaryMapperBO = aviaryMapperBO;
    }


    public AnomalyBO toBO(AnomalyDTO dto) {
        AviaryBO aviary = aviaryMapperBO.toBO(findAviaryByIdUseCase.invoke(dto.aviaryId()).orElseThrow());
        return new AnomalyBO(
                dto.id(),
                dto.description(),
                aviary
        );
    }

    public AnomalyDTO toDTO(AnomalyBO bo) {
        return new AnomalyDTO(
                bo.getId(),
                bo.getDescription(),
                bo.getAviary().getId()
        );
    }

    public List<AnomalyBO> toBOList(List<AnomalyDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<AnomalyDTO> toDTOList(List<AnomalyBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
