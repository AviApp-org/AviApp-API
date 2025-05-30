package br.com.aviapp.api.application.usecases.water;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.gateways.WaterRepository;
import br.com.aviapp.api.application.mappers.WaterMapperBO;
import br.com.aviapp.api.domain.entities.WaterBO;

public class CreateWaterRecordUseCase {

    private final WaterRepository repository;
    private final WaterMapperBO mapperBO;

    public CreateWaterRecordUseCase(WaterRepository repository, WaterMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public WaterDTO invoke (WaterDTO waterDTO) {
        WaterBO waterBO = mapperBO.toBO(waterDTO);
        return repository.createWaterReport(mapperBO.toDTO(waterBO));
    }
}
