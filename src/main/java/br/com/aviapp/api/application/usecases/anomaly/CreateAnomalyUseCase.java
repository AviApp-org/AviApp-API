package br.com.aviapp.api.application.usecases.anomaly;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.application.gateways.IAnomaly;
import br.com.aviapp.api.application.mappers.AnomalyMapperBO;
import br.com.aviapp.api.domain.entities.AnomalyBO;

public class CreateAnomalyUseCase {
    private final IAnomaly anomalyRepository;
    private final AnomalyMapperBO anomalyMapperBO;

    public CreateAnomalyUseCase(IAnomaly anomalyRepository, AnomalyMapperBO anomalyMapperBO) {
        this.anomalyRepository = anomalyRepository;
        this.anomalyMapperBO = anomalyMapperBO;
    }

    public AnomalyDTO  invoke(AnomalyDTO anomalyDTO) {
        AnomalyBO anomalyBO = anomalyMapperBO.toBO(anomalyDTO);
        AnomalyDTO validatedAnomaly = anomalyMapperBO.toDTO(anomalyBO);
        return anomalyRepository.createAnomaly(validatedAnomaly);
    }
}
