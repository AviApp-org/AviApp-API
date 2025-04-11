package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.AviaryDTO;

public interface AviaryRepository {
    
    AviaryDTO createAviary(AviaryDTO aviaryDTO);

    Optional<List<AviaryDTO>> listAllAviarysByBatchId(Long batchId);

    Optional<AviaryDTO> findAviaryById(Long aviaryId);

    List<AviaryDTO> listAllAviarys();

    Optional<AviaryDTO> updateAviary(Long aviaryId, AviaryDTO aviaryDTO);

    void deleteAviary(Long aviaryId);

    
}
