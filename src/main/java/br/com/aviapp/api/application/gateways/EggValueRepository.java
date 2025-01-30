package br.com.aviapp.api.application.gateways;

import java.util.List;

import br.com.aviapp.api.application.dto.EggValueDTO;

public interface EggValueRepository {

    EggValueDTO createEggValue(EggValueDTO eggValueDTO);

    List<EggValueDTO> listAllEggValues();

    void deleteEggValue(Long eggValueId);
}
