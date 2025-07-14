package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAnomalyEntity;

public class AnomalyMapperEntity {

    public MySqlAnomalyEntity toEntity(AnomalyDTO dto) {
        MySqlAnomalyEntity anomaly = new MySqlAnomalyEntity();
        anomaly.setId(dto.id());
        anomaly.setDescription(dto.description());

        return anomaly;
    }

    public AnomalyDTO toDTO(MySqlAnomalyEntity entity) {
        return new AnomalyDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getAviary().getId()
        );
    }

    public List<AnomalyDTO> toDTOList(List<MySqlAnomalyEntity> anomalies) {
        return anomalies.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlAnomalyEntity> toEntityList(List<AnomalyDTO> anomalies) {
        return anomalies.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
