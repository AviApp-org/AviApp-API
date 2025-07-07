package br.com.aviapp.api.application.usecases.anomaly;

import br.com.aviapp.api.application.gateways.IAnomaly;

public class DeleteAnomalyUseCase {
    private final IAnomaly anomalyRepository;

    public DeleteAnomalyUseCase(IAnomaly anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    public void invoke(Long anomalyID) {
        anomalyRepository.deleteAnomaly(anomalyID);
    }
}
