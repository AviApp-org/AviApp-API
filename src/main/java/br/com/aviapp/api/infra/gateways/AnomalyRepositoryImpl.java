package br.com.aviapp.api.infra.gateways;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.application.gateways.AnomalyRepository;
import br.com.aviapp.api.infra.mappers.AnomalyMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAnomalyEntity;
import br.com.aviapp.api.infra.mysql.repository.AnomalyRepositoryJPA;

@Repository
public class AnomalyRepositoryImpl implements AnomalyRepository {

    private final AnomalyRepositoryJPA repositoryJPA;
    private final AnomalyMapperEntity mapperEntity;

    public AnomalyRepositoryImpl(AnomalyRepositoryJPA repositoryJPA, AnomalyMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
    }

    @Override
    public AnomalyDTO createAnomaly(AnomalyDTO anomalyDTO) {
        MySqlAnomalyEntity anomalyEntity = mapperEntity.toEntity(anomalyDTO);
        MySqlAnomalyEntity savedAnomalyEntity = repositoryJPA.save(anomalyEntity);
        return mapperEntity.toDTO(savedAnomalyEntity);
    }

    @Override
    public List<AnomalyDTO> listAllAnomalies() {
        List<MySqlAnomalyEntity> anomalies = repositoryJPA.findAll();
        return mapperEntity.toDTOList(anomalies);
    }

    @Override
    public void deleteAnomaly(Long anomalyId) {
        repositoryJPA.deleteById(anomalyId);
    }

}
