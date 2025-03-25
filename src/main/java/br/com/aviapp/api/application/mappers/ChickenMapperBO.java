package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.domain.entities.ChickenBO;

public class ChickenMapperBO {
    
    public ChickenBO toBO(ChickenDTO dto) {
        return new ChickenBO(
            dto.id(),
            dto.currentRoosters(),
            dto.currentChickens()
        );
    }

    public ChickenDTO toDTO(ChickenBO bo) {
        return new ChickenDTO(
            bo.getId(),
            bo.getCurrentRoosters(),
            bo.getCurrentChickens()
        );
    }

    public List<ChickenBO> toBOList(List<ChickenDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<ChickenDTO> toDTOList(List<ChickenBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
