package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;

import java.util.List;

public class ListActiveBatchesByFarmUseCase {

    private final IBatch batchRepository;


    public ListActiveBatchesByFarmUseCase(IBatch batchRepository) {
        this.batchRepository = batchRepository;
    }

    public List<BatchDTO> invoke(Long farmId) {

        return batchRepository.listBatchesActiveByFarm(farmId);
    }
}
