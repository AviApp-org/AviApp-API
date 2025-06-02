package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.usecases.water.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/water")
public class WaterController {

    private final CreateWaterRecordUseCase createWaterRecord;
    private final ListAllWaterRecordsUseCase listWaterRecords;
    private final DeleteWaterRecordUseCase deleteWaterRecord;
    private final ListWaterRecordsByAviaryUseCase listWaterRecordsByAviary;
    private final GetWaterRecordByDateUseCase getWaterRecordByDate;

    public WaterController(CreateWaterRecordUseCase createWaterRecord, ListAllWaterRecordsUseCase listWaterRecords, DeleteWaterRecordUseCase deleteWaterRecord,
                           ListWaterRecordsByAviaryUseCase listWaterRecordsByAviary, GetWaterRecordByDateUseCase getWaterRecordByDate) {
        this.createWaterRecord = createWaterRecord;
        this.listWaterRecords = listWaterRecords;
        this.deleteWaterRecord = deleteWaterRecord;
        this.listWaterRecordsByAviary = listWaterRecordsByAviary;
        this.getWaterRecordByDate = getWaterRecordByDate;
    }

    @PostMapping
    public ResponseEntity<WaterDTO> createWaterRecord(@RequestBody WaterDTO waterDTO){
        WaterDTO water = createWaterRecord.invoke(waterDTO);
        URI location = URI.create("/api/water/" + water.id());
        return ResponseEntity.created(location).body(water);
    }

    @GetMapping
    public ResponseEntity<List<WaterDTO>> listAllWaterRecords(){
        List<WaterDTO> waterRecords = listWaterRecords.invoke();
        return ResponseEntity.ok(waterRecords);
    }

    @GetMapping("/aviary/{aviaryId}")
    public ResponseEntity<List<WaterDTO>> listWaterRecordsByAviary(@PathVariable Long aviaryId){
        List<WaterDTO> waterRecords = listWaterRecordsByAviary.invoke(aviaryId);
        return ResponseEntity.ok(waterRecords);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<WaterDTO>> listWaterRecordsByDate(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date){
        List<WaterDTO> waterRecords = getWaterRecordByDate.invoke(date);
        return ResponseEntity.ok(waterRecords);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterRecord(@PathVariable Long id){
        deleteWaterRecord.invoke(id);
        return ResponseEntity.noContent().build();
    }


}
