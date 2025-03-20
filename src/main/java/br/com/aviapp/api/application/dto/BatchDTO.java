package br.com.aviapp.api.application.dto;


import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumBatchStatus;

public record BatchDTO(Long id, String name,
                       Date startDate, EnumBatchStatus status, Long farmId) {

}
