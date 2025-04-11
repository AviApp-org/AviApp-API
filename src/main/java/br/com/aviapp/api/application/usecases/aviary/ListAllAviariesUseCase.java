package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.AviaryRepository;

import java.util.List;

public class ListAllAviariesUseCase {

    private final AviaryRepository repository;

    public ListAllAviariesUseCase(AviaryRepository repository) {
        this.repository = repository;
    }

    public List<AviaryDTO> invoke() {
        return repository.listAllAviarys();
    }

}
