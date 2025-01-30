package br.com.aviapp.api.application.usecases.anomaly;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.application.gateways.AnomalyRepository;
import br.com.aviapp.api.application.mappers.AnomalyMapperBO;

public class ListAllAnomaliesUseCase {
    private final AnomalyRepository anomalyRepository;
    private final AnomalyMapperBO anomalyMapperBO;

    public ListAllAnomaliesUseCase(AnomalyRepository anomalyRepository, AnomalyMapperBO anomalyMapperBO) {
        this.anomalyRepository = anomalyRepository;
        this.anomalyMapperBO = anomalyMapperBO;
    }

    public List<AnomalyDTO> invoke() {
        List<AnomalyDTO> anomalies = anomalyRepository.listAllAnomalies();
        return anomalies.stream()
                .map(anomalyMapperBO::toBO)
                .map(anomalyMapperBO::toDTO)
                .collect(Collectors.toList());
    }
}
