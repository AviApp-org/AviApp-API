package br.com.aviapp.api.domain.utils;

import br.com.aviapp.api.domain.errors.BusinessRuleException;

public interface CreationValidator {

    void validateForCreation() throws BusinessRuleException;
}
