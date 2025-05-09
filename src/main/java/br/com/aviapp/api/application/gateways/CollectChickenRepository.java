package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectChickenDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CollectChickenRepository {

    CollectChickenDTO createCollectChickenData(CollectChickenDTO collectChickenDataDTO);

    List<CollectChickenDTO> listCollectChickenData();

    List<CollectChickenDTO> listChickenCollectByAviary(Long aviaryId);

    List<CollectChickenDTO> getChickenCollectByDate(LocalDateTime date);
}
