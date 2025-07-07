package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectChickenDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICollectChicken {

    CollectChickenDTO createCollectChickenData(CollectChickenDTO collectChickenDataDTO);

    List<CollectChickenDTO> listCollectChickenData();

    List<CollectChickenDTO> listChickenCollectByAviary(Long aviaryId);

    List<CollectChickenDTO> getChickenCollectByDate(LocalDate date);

    List<CollectChickenDTO> getChickenCollectByAviaryAndDate(Long aviaryId, LocalDate date);

    void deleteCollectChickenData(Long id);

    Optional<CollectChickenDTO> getCollectChickenDataById(Long id);
}
