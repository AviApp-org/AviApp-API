package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.dto.CollectChickenDTO;

import java.util.List;

public interface CollectChickenRepository {

    CollectChickenDTO createCollectChickenData(CollectChickenDTO collectChickenDataDTO);

    List<CollectChickenDTO> listCollectChickenData();

    List<CollectChickenDTO> listChickenCollectByEmployee(Long employeeId);

    List<CollectChickenDTO> listChickenCollectByAviary(Long aviaryId);


}
