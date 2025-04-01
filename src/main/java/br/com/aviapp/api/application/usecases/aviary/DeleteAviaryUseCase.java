package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.exceptions.NotFoundException;
import br.com.aviapp.api.application.gateways.AviaryRepository;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.ResourceNotFoundException;
import br.com.aviapp.api.domain.utils.DeletionValidator;

public class DeleteAviaryUseCase {

    private final AviaryRepository aviaryRepository;
    private final AviaryMapperBO aviaryMapperBO;

    public DeleteAviaryUseCase(AviaryRepository aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        this.aviaryRepository = aviaryRepository;
        this.aviaryMapperBO = aviaryMapperBO;
    }

    public void invoke(Long aviaryId) throws BusinessRuleException, ResourceNotFoundException {
        AviaryBO aviaryBO = aviaryMapperBO.toBO(aviaryRepository.findAviaryById(aviaryId).get()) ;

        if (aviaryBO == null) {
            throw new NotFoundException(aviaryId);
        }

        DeletionValidator.validateDeletion(aviaryBO, "Aviario não encontrado");

        aviaryRepository.deleteAviary(aviaryId);
    }
}
