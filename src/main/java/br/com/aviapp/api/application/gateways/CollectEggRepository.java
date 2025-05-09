package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CollectEggRepository {

    CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO);

    List<CollectEggDataDTO> listEggCollectByAviary(Long aviaryId);

    List<CollectEggDataDTO> getAllEggCollects();

    List<CollectEggDataDTO> getEggCollectByDate(LocalDateTime date);
}
