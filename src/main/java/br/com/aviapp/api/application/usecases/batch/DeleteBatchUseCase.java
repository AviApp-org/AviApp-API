package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.gateways.BatchRepository;

public class DeleteBatchUseCase {

    private final BatchRepository repository;


    public DeleteBatchUseCase(BatchRepository repository) {
        this.repository = repository;
    }

    public void invoke (Long batchId){

        repository.deleteBatch(batchId);
    }
}
