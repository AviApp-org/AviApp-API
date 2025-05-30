package br.com.aviapp.api.application.usecases.water;

import br.com.aviapp.api.application.gateways.WaterRepository;

public class DeleteWaterRecordUseCase {

    private final WaterRepository waterRepository;

    public DeleteWaterRecordUseCase(WaterRepository waterRepository) {
        this.waterRepository = waterRepository;
    }

    public void invoke(Long id) {
        waterRepository.deleteWaterRecord(id);
    }
}
