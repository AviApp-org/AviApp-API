package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.gateways.IBatch;

public class DeleteBatchUseCase {

    private final IBatch repository;


    public DeleteBatchUseCase(IBatch repository) {
        this.repository = repository;
    }

    public void invoke (Long batchId){

        repository.deleteBatch(batchId);
    }
}
