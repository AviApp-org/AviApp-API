package br.com.aviapp.api.application.usecases.eggValue;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.application.gateways.EggValueRepository;
import br.com.aviapp.api.application.mappers.EggValueMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllEggValuesUseCase {

    private final EggValueRepository repository;
    private final EggValueMapperBO mapperBO;

    public ListAllEggValuesUseCase(EggValueRepository repository, EggValueMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public List<EggValueDTO> invoke() {
        List<EggValueDTO> values = repository.listAllEggValues();
        return values.stream().
                map(mapperBO::toBO).
                map(mapperBO::toDTO).
                collect(Collectors.toList());
    }
}
