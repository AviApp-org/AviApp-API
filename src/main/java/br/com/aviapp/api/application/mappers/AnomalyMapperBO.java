package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.domain.entities.AnomalyBO;

public class AnomalyMapperBO {

    public AnomalyBO toBO(AnomalyDTO dto) {
        return new AnomalyBO(
            dto.id(),
            dto.description()
        );
    }

    public AnomalyDTO toDTO(AnomalyBO bo) {
        return new AnomalyDTO(
            bo.getId(),
            bo.getDescription()
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
