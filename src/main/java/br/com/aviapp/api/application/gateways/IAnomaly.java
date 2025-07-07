package br.com.aviapp.api.application.gateways;

import java.util.List;

import br.com.aviapp.api.application.dto.AnomalyDTO;

public interface IAnomaly {

     AnomalyDTO createAnomaly(AnomalyDTO anomalyDTO);

     List<AnomalyDTO> listAllAnomalies();

     void deleteAnomaly(Long anomalyId);
}
