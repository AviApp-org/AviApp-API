package br.com.aviapp.api.application.gateways;

import java.util.List;

import br.com.aviapp.api.application.dto.CollectDTO;

public interface CollectRepository {
    
    CollectDTO createCollect(CollectDTO collectDTO);

    void deleteCollect(Long collectId);
}
