package br.com.aviapp.api.application.usecases.collect;

import br.com.aviapp.api.application.gateways.CollectRepository;

public class DeleteCollectUseCase {
    private final CollectRepository collectRepository;

    public DeleteCollectUseCase(CollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

    public void invoke(Long collectId) {
        collectRepository.deleteCollect(collectId);
    }
}
