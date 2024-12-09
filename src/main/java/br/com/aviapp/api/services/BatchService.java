package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
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




    public Optional<MySqlBatchEntity> getBatchById(Long id) {
        var batch = batchRepository.findById(id);
        return batch;
    }

}