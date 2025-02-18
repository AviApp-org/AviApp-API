package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectChickenDataDTO;

import java.util.List;

public interface CollectChickenDataRepository {

    CollectChickenDataDTO createCollectChickenData(CollectChickenDataDTO collectChickenDataDTO);
    List<CollectChickenDataDTO> listCollectChickenData();

}
