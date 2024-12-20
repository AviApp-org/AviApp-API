package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.BatchDTO;

public interface BatchRepository {
    BatchDTO createBatch(BatchDTO batchDTO);

    void deleteBatch(Long batchID);

    Optional<BatchDTO> findBatch(Long batchID);

    List<BatchDTO> findBatchesByFarmId(Long farmId);
}