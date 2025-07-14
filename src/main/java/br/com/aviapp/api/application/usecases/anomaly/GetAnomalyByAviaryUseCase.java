package br.com.aviapp.api.application.usecases.anomaly;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.application.gateways.IAnomaly;

import java.util.List;

public class GetAnomalyByAviaryUseCase {

    private final IAnomaly repository;

    public GetAnomalyByAviaryUseCase(IAnomaly repository) {
        this.repository = repository;
    }

    public List<AnomalyDTO> invoke(Long aviaryId) {
        return repository.getAnomamliesByAviary(aviaryId);
    }
}
