package br.com.aviapp.api.application.usecases.anomaly;

import br.com.aviapp.api.application.gateways.AnomalyRepository;

public class DeleteAnomalyUseCase {
    private final AnomalyRepository anomalyRepository;

    public DeleteAnomalyUseCase(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    public void invoke(Long anomalyID) {
        anomalyRepository.deleteAnomaly(anomalyID);
    }
}
