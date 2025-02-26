package br.com.aviapp.api.application.usecases.collect;

import br.com.aviapp.api.application.gateways.CollectRepository;

public class deleteCollectUseCase {
    private final CollectRepository collectRepository;

    public deleteCollectUseCase(CollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

    public void invoke(Long collectId) {
        collectRepository.deleteCollect(collectId);
    }
}
