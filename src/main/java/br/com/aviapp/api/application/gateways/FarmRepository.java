package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.FarmDTO;

public interface FarmRepository {

    FarmDTO createFarm(FarmDTO farm);

    List<FarmDTO> listAllFarms();

    Optional<FarmDTO> findFarm(Long farmID);

    void deleteFarm(Long farmID);

    Optional<FarmDTO> updateFarm(Long farmID, FarmDTO farm);


}
