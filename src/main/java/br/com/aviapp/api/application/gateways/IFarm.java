package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.FarmDTO;

public interface IFarm {

    FarmDTO createFarm(FarmDTO farm);

    List<FarmDTO> listAllFarms();

    Optional<FarmDTO> findFarm(Long farmID);

    Optional<FarmDTO> findFarmByClient(Long clientId);

    void deleteFarm(Long farmID);

    Optional<FarmDTO> updateFarm(Long farmID, FarmDTO farm);


}
