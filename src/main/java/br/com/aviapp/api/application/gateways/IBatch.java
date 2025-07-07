package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.BatchDTO;

public interface IBatch {
    BatchDTO createBatch(BatchDTO batchDTO);

    void deleteBatch(Long batchID);

    Optional<BatchDTO> findBatch(Long batchID);

    List<BatchDTO> findBatchesByFarmId(Long farmId);

    List<BatchDTO> listBatchesActiveByFarm(Long farmId);

    Optional<BatchDTO> updateBatch(Long id, BatchDTO batchDTO);

    void save(BatchDTO batchDTO);
    
}
