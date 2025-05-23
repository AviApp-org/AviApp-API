package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.application.dto.EggDetailPercentageDTO;
import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.entities.EggDetailPercentageVO;
import br.com.aviapp.api.domain.errors.InvalidParamError;

import java.util.List;
import java.util.stream.Collectors;

public class EggDetailMapperBO {

    public EggDetailBO toBO(EggDetailDTO dto) throws InvalidParamError {
        return new EggDetailBO(
                dto.type(),
                dto.quantity());
    }

    public EggDetailDTO toDTO(EggDetailBO bo) {
        return new EggDetailDTO(
                bo.getType(),
                bo.getQuantity());
    }

    public EggDetailPercentageVO toPercentageBO(EggDetailPercentageDTO dto) throws InvalidParamError {
        return new EggDetailPercentageVO(
                dto.type(),
                0,
                dto.percentage());
    }

    public EggDetailPercentageDTO toPercentageDTO(EggDetailPercentageVO bo) {
        return new EggDetailPercentageDTO(
                bo.getType(),
                bo.getPercentage());
    }

    public List<EggDetailPercentageDTO> toPercentageDTOList(List<EggDetailPercentageVO> bos) {
        return bos.stream()
                .map(this::toPercentageDTO)
                .collect(Collectors.toList());
    }

    public List<EggDetailPercentageVO> toBOListPercentagem(List<EggDetailPercentageDTO> dtos) {
        return dtos.stream()
                .map(dto -> {
                    try {
                        return this.toPercentageBO(dto);
                    } catch (InvalidParamError e) {
                        // Você pode escolher como lidar com essa exceção
                        // Por exemplo, lançar uma RuntimeException ou registrar o erro
                        throw new RuntimeException("Erro ao converter EggDetailPercentageDTO para EggDetailPercentageBO", e);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<EggDetailBO> toBOList(List<EggDetailDTO> dtos) {
        return dtos.stream()
                .map(dto -> {
                    try {
                        return this.toBO(dto);
                    } catch (InvalidParamError e) {
                        // Você pode escolher como lidar com essa exceção
                        // Por exemplo, lançar uma RuntimeException ou registrar o erro
                        throw new RuntimeException("Erro ao converter EggDetailDTO para EggDetailBO", e);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<EggDetailDTO> toDTOList(List<EggDetailBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
