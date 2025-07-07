package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.application.mappers.FarmMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllFarmsUseCase {

    private final IFarm repository;
    private final FarmMapperBO mapperBO;

    public ListAllFarmsUseCase(IFarm repository, FarmMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public List<FarmDTO> invoke() {
        return repository.listAllFarms().stream()
                .map(mapperBO::toBO)
                .map(mapperBO::toDTO)
                .collect(Collectors.toList());
    }
}
