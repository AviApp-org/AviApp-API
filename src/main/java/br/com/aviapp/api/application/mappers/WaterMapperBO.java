package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.WaterBO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WaterMapperBO {

    private final AviaryMapperBO aviaryMapperBO;
    private final LookUpRepository lookUpRepository;

    public WaterMapperBO(AviaryMapperBO aviaryMapperBO, LookUpRepository lookUpRepository) {
        this.aviaryMapperBO = aviaryMapperBO;
        this.lookUpRepository = lookUpRepository;
    }

    public WaterBO toBO(WaterDTO dto) {
        Optional<AviaryDTO> aviary = lookUpRepository.findAviaryDTOById(dto.aviaryId());

        if (aviary.isEmpty()) {
            throw new IllegalArgumentException("Aviário não encontrado");
        }

        return new WaterBO(
                dto.id(),
                aviaryMapperBO.toBO(aviary.get()),
                dto.volume(),
                dto.collectionDate()
        );
    }

    public WaterDTO toDTO(WaterBO bo) {
        return new WaterDTO(
                bo.getId(),
                bo.getAviary().getId(),
                bo.getVolume(),
                bo.getCollectionDate()
        );
    }


    public List<WaterBO> toBOList(List<WaterDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<WaterDTO> toDTOList(List<WaterBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }}
