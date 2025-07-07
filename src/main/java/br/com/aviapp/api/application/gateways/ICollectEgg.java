package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICollectEgg {

    CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO);

    List<CollectEggDataDTO> listEggCollectByAviary(Long aviaryId);

    List<CollectEggDataDTO> getAllEggCollects();

    List<CollectEggDataDTO> getEggCollectByDate(LocalDate date);

    List<CollectEggDataDTO> getEggCollectByAviaryAndDate(Long aviaryId, LocalDate date);

    void deleteCollectEggData(Long id);

    Optional<CollectEggDataDTO> getCollectEggDataById(Long id);
}
