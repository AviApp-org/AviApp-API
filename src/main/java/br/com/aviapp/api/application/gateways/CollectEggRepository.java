package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.dto.CollectEggDataDTO;

import java.util.List;

public interface CollectEggRepository {

    CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO);

    List<CollectEggDataDTO> listEggCollectByEmployee(Long employeeId);

    List<CollectEggDataDTO> listEggCollectByAviary(Long aviaryId);

    List<CollectEggDataDTO> getAllEggCollects();

}
