package br.com.aviapp.api.application.usecases.water;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.gateways.IWater;
import br.com.aviapp.api.application.mappers.WaterMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListWaterRecordsByAviaryUseCase {

    private final IWater waterRepository;
    private final WaterMapperBO mapperBO;

    public ListWaterRecordsByAviaryUseCase(IWater waterRepository, WaterMapperBO mapperBO) {
        this.waterRepository = waterRepository;
        this.mapperBO = mapperBO;
    }

    public List<WaterDTO> invoke(Long aviaryId) {
        return waterRepository.listWaterRecordByAviary(aviaryId).stream()
                .map(mapperBO::toBO)
                .map(mapperBO::toDTO)
                .collect(Collectors.toList());
    }
}
