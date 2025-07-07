package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.IAviary;

import java.util.List;

public class ListAllAviariesUseCase {

    private final IAviary repository;

    public ListAllAviariesUseCase(IAviary repository) {
        this.repository = repository;
    }

    public List<AviaryDTO> invoke() {
        return repository.listAllAviarys();
    }

}
