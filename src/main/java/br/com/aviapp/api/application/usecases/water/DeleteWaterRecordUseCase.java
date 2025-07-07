package br.com.aviapp.api.application.usecases.water;

import br.com.aviapp.api.application.gateways.IWater;

public class DeleteWaterRecordUseCase {

    private final IWater waterRepository;

    public DeleteWaterRecordUseCase(IWater waterRepository) {
        this.waterRepository = waterRepository;
    }

    public void invoke(Long id) {
        waterRepository.deleteWaterRecord(id);
    }
}
