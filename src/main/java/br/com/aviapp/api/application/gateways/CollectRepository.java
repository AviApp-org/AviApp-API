package br.com.aviapp.api.application.gateways;


public interface CollectRepository {
    
    CollectDTO createCollect(CollectDTO collectDTO);

    void deleteCollect(Long collectId);
}
