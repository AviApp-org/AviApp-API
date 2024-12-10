package br.com.aviapp.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.FarmRepository;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public MySqlFarmEntity findBatchOrThrow(FarmDTO farmDTO) {

        return farmRepository.findById(farmDTO.getId())
                .orElseThrow(() -> new RuntimeException("errorMessage "));
    }

    /*public FarmDTO toFarmDTO(MySqlFarmEntity farmEntity) {
        FarmDTO farmDTO = new FarmDTO();
        batchDTO.setId(batchEntity.getId());
        batchDTO.setFarmId(FarmDTO.fromEntity(batchEntity.getFarmId()));
        // Mapear outros campos de MySqlBatchEntity para BatchDTO, se necessário
        return batchDTO;
    }*/
}
