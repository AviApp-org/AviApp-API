package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListChickenCollectsByEmployeeUseCase {
    private final CollectChickenRepository collectRepository;
    private final CollectChickenMapperBO collectMapper;

    public ListChickenCollectsByEmployeeUseCase(CollectChickenRepository collectRepository, CollectChickenMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectChickenDTO> invoke(Long employeeId) {
        return collectRepository.listChickenCollectByEmployee(employeeId).stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
