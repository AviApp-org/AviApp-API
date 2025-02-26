package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.ChickenDTO;

public interface ChickenRepository {
    
    ChickenDTO createChicken(ChickenDTO chickenDTO);

    List<ChickenDTO> listAllChicken();

    Optional<ChickenDTO> updateChicken(ChickenDTO chickenDTO, Long id);

}
