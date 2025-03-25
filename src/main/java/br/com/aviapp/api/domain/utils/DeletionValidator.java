package br.com.aviapp.api.domain.utils;

import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.ResourceNotFoundException;

public class DeletionValidator {
    /**
     * Valida se uma entidade pode ser excluída
     *
     * @param entity A entidade a ser validada
     * @throws BusinessRuleException se a entidade não puder ser excluída
     * @throws ResourceNotFoundException se a entidade for nula
     */
    public static void validateDeletion(DeletableEntity entity) throws BusinessRuleException, ResourceNotFoundException {
        if (entity == null) {
            throw new ResourceNotFoundException("Entidade não encontrada");
        }

        entity.validateDeletion();
    }

    /**
     * Valida se uma entidade pode ser excluída com uma mensagem personalizada para o caso de não encontrada
     *
     * @param entity A entidade a ser validada
     * @param notFoundMessage Mensagem personalizada para o caso de entidade não encontrada
     * @throws BusinessRuleException se a entidade não puder ser excluída
     * @throws ResourceNotFoundException se a entidade for nula
     */
    public static void validateDeletion(DeletableEntity entity, String notFoundMessage)
            throws BusinessRuleException, ResourceNotFoundException {
        if (entity == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        entity.validateDeletion();
    }
}
