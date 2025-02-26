package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.domain.entities.EggValueBO;

public class EggValueMapperBO {

    public EggValueBO toBO(EggValueDTO dto) {
        return new EggValueBO(
                dto.id(),
                dto.egg(),
                dto.timeStamp(),
                dto.value());
    }

    public EggValueDTO toDTO(EggValueBO bo) {
        return new EggValueDTO(
                bo.getId(),
                bo.getEggType(),
                bo.getTimestamp(),
                bo.getValue());
    }

    public List<EggValueBO> toBOList(List<EggValueDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<EggValueDTO> toDTOList(List<EggValueBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
