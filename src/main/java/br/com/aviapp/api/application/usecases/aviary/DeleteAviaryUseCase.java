package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.exceptions.NotFoundException;
import br.com.aviapp.api.application.gateways.IAviary;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.ResourceNotFoundException;

public class DeleteAviaryUseCase {

    private final IAviary aviaryRepository;
    private final AviaryMapperBO aviaryMapperBO;

    public DeleteAviaryUseCase(IAviary aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        this.aviaryRepository = aviaryRepository;
        this.aviaryMapperBO = aviaryMapperBO;
    }

    public void invoke(Long aviaryId) throws BusinessRuleException, ResourceNotFoundException {
        AviaryBO aviaryBO = aviaryMapperBO.toBO(aviaryRepository.findAviaryById(aviaryId)
                .orElseThrow(() -> new NotFoundException(aviaryId)));

        aviaryBO.validateDeletion();

        aviaryRepository.deleteAviary(aviaryId);
    }
}
