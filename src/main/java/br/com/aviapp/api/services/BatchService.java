package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.BatchDTO;
import br.com.aviapp.api.domain.dto.FarmDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.BatchRepository;
import br.com.aviapp.api.infra.mysql.repository.FarmRepository;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private FarmRepository farmRepository;

    public List<MySqlBatchEntity> findAll() {
        return batchRepository.findAll();
    }

    public BatchDTO findById(Long id) {
        Optional<MySqlBatchEntity> batchEntityOptional = batchRepository.findById(id);
        if (batchEntityOptional.isPresent()) {
            MySqlBatchEntity BatchEntity = batchEntityOptional.get();
            return toBatchDTO(BatchEntity);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    /* 
    public Long save(BatchDTO batchDTO) {
         MySqlBatchEntity batchDTO = batchService.findBatchOrThrow(BatchDTO.getBatchId());
 
         var entity = new MySqlBatchEntity(
                 null,
                 BatchDTO.getName(),
                 batchDTO);
 
         var savedBatch = batchRepository.save(entity);
         return savedBatch.getId();
     }
 
     public void deleteBatch(Long id) {
         var batchExiste = batchRepository.existsById(id);
 
         if (batchExiste) {
             batchRepository.deleteById(id);
         }
     }
    */ 
    

    public List<BatchDTO> listBatches() {
        List<MySqlBatchEntity> aviaries = batchRepository.findAll();
        return aviaries.stream()
                .map(this::toBatchDTO)
                .collect(Collectors.toList());
    }

    public Optional<MySqlBatchEntity> getBatchById(Long id) {
        var batch = batchRepository.findById(id);
        return batch;
    }

    /*public BatchDTO updateBatch(Long id, BatchDTO BatchDTO) {
        Optional<MySqlBatchEntity> optionalBatch = batchRepository.findById(id);

        if (optionalBatch.isPresent()) {
            MySqlBatchEntity existingBatch = optionalBatch.get();

            existingBatch.setName(BatchDTO.getName());
            existingBatch.setBatchId(batchService.findBatchOrThrow(BatchDTO.getBatchId()));

            MySqlBatchEntity updatedBatch = BatchRepository.save(existingBatch);

            return toBatchDTO(updatedBatch);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }
*/
    public MySqlBatchEntity findBatchOrThrow(BatchDTO batchDTO) {

        return batchRepository.findById(batchDTO.getId())
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

    public BatchDTO toBatchDTO(MySqlBatchEntity batchEntity) {
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setId(batchEntity.getId());
        batchDTO.setFarmId(FarmDTO.fromEntity(batchEntity.getFarmId()));
        // Mapear outros campos de MySqlBatchEntity para BatchDTO, se necessário
        return batchDTO;
    }

}
