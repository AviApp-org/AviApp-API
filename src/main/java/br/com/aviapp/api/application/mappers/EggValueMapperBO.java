package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.domain.entities.BatchBO;
import br.com.aviapp.api.domain.entities.EggValueBO;

public class EggValueMapperBO {

    private final ILookUp lookupRepository;
    private final BatchMapperBO batchMapperBO;

    public EggValueMapperBO(ILookUp lookupRepository, BatchMapperBO batchMapperBO) {
        this.lookupRepository = lookupRepository;
        this.batchMapperBO = batchMapperBO;
    }

    public EggValueBO toBO(EggValueDTO dto) {
        BatchBO batchBO = batchMapperBO.toBO(lookupRepository.findBatchDTOById(dto.batchId()).orElseThrow());

        return new EggValueBO(
                dto.id(),
                dto.egg(),
                dto.timeStamp(),
                dto.value(),
                batchBO);
    }

    public EggValueDTO toDTO(EggValueBO bo) {
        return new EggValueDTO(
                bo.getId(),
                bo.getEggType(),
                bo.getTimestamp(),
                bo.getValue(),
                bo.getBatch().getId());
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
