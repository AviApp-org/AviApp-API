package br.com.aviapp.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.BatchDTO;
import br.com.aviapp.api.domain.dto.FarmDTO;
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

    public MySqlBatchEntity toEntity(BatchDTO batchDTO) {
        MySqlBatchEntity batchEntity = new MySqlBatchEntity();
        batchEntity.setId(batchDTO.getId());

        // Obter a entidade MySqlFarmEntity a partir do repositório usando o ID do
        // FarmDTO
        Optional<MySqlFarmEntity> optionalFarmEntity = farmRepository.findById(batchDTO.getFarmId().getId());

        if (optionalFarmEntity.isPresent()) {
            MySqlFarmEntity farmEntity = optionalFarmEntity.get();
            batchEntity.setFarmId(farmEntity);
        } else {
            // Lançar uma exceção ou tratar o caso em que o FarmDTO não corresponde a uma
            // entidade válida
            throw new RuntimeException("Fazenda não encontrada para o ID: " + batchDTO.getFarmId().getId());
        }

        return batchEntity;
    }

    /*public FarmDTO toFarmDTO(MySqlFarmEntity farmEntity) {
        FarmDTO farmDTO = new FarmDTO();
        batchDTO.setId(batchEntity.getId());
        batchDTO.setFarmId(FarmDTO.fromEntity(batchEntity.getFarmId()));
        // Mapear outros campos de MySqlBatchEntity para BatchDTO, se necessário
        return batchDTO;
    }*/
}
