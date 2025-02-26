package br.com.aviapp.api.application.gateways;

import java.util.List;

import br.com.aviapp.api.application.dto.CollectDTO;

public interface CollectRepository {
    
    CollectDTO createCollect(CollectDTO collectDTO);

    List<CollectDTO> listCollectByEmployee(Long employeeId);

    List<CollectDTO> listCollectByAviary(Long aviaryId);

    List<CollectDTO> getAllCollects();  

    void deleteCollect(Long collectId);
}
