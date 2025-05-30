package br.com.aviapp.api.application.usecases.water;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.gateways.WaterRepository;
import br.com.aviapp.api.application.mappers.WaterMapperBO;

import java.time.LocalDate;
import java.util.List;

public class GetWaterRecordByDateUseCase {

    private final WaterRepository waterRepository;
    private final WaterMapperBO mapperBO;

    public GetWaterRecordByDateUseCase(WaterRepository waterRepository, WaterMapperBO mapperBO) {
        this.waterRepository = waterRepository;
        this.mapperBO = mapperBO;
    }

    public List<WaterDTO> invoke(LocalDate date) {
        return waterRepository.getWaterRecordByDate(date);
    }
}
