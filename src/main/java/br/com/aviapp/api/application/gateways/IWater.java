package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.WaterDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IWater {

     WaterDTO createWaterReport(WaterDTO waterDTO);

     List<WaterDTO> listWaterRecordByAviary(Long aviaryId);

     List<WaterDTO> getAllWaterRecords();

     List<WaterDTO> getWaterRecordByDate(LocalDate date);

     List<WaterDTO> getWaterRecordByAviaryAndDate(Long aviaryId, LocalDate date);

     void deleteWaterRecord(Long id);

     Optional<WaterDTO> getWaterRecordById(Long id);
}
