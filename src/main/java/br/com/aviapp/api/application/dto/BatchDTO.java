package br.com.aviapp.api.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record BatchDTO(Long id, String name,
                       Date startDate, boolean isActive, Long farmId) {

}
