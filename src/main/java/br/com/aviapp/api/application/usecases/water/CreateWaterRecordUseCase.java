package br.com.aviapp.api.application.usecases.water;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.gateways.IWater;
import br.com.aviapp.api.application.mappers.WaterMapperBO;
import br.com.aviapp.api.domain.entities.WaterBO;

public class CreateWaterRecordUseCase {

    private final IWater repository;
    private final WaterMapperBO mapperBO;

    public CreateWaterRecordUseCase(IWater repository, WaterMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public WaterDTO invoke (WaterDTO waterDTO) {
        WaterBO waterBO = mapperBO.toBO(waterDTO);
        return repository.createWaterReport(mapperBO.toDTO(waterBO));
    }
}
