package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.AviaryDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.repository.AviaryRepository;

@Service
public class AviaryService {

    @Autowired
    private AviaryRepository aviaryRepository;

    @Autowired
    private BatchService batchService;

    public List<MySqlAviaryEntity> findAll() {
        return aviaryRepository.findAll();
    }

    public AviaryDTO findById(Long id) {
        Optional<MySqlAviaryEntity> aviaryEntityOptional = aviaryRepository.findById(id);
        if (aviaryEntityOptional.isPresent()) {
            MySqlAviaryEntity aviaryEntity = aviaryEntityOptional.get();
            return toAviaryDTO(aviaryEntity);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    public Long save(AviaryDTO aviaryDTO) {

        MySqlBatchEntity batchDTO = batchService.findBatchOrThrow(aviaryDTO.getBatchId());

        var entity = new MySqlAviaryEntity(
            null,
            aviaryDTO.getName(),
            batchDTO
        );

        var savedAviary = aviaryRepository.save(entity);
        return savedAviary.getId();
    }

    public AviaryDTO toAviaryDTO(MySqlAviaryEntity entity) {
        AviaryDTO dto = new AviaryDTO();
        dto.setName(entity.getName());
        dto.setBatchId(entity.getBatchId().getId());
        // Mapear outros campos, se necessário
        return dto;
    }

    public void deleteAviary(Long id) {
        var aviaryExiste = aviaryRepository.existsById(id);

        if (aviaryExiste) {
            aviaryRepository.deleteById(id);
        }
    }

    public List<AviaryDTO> listAviaries() {
        List<MySqlAviaryEntity> aviaries = aviaryRepository.findAll();
        return aviaries.stream()
                .map(this::toAviaryDTO)
                .collect(Collectors.toList());
    }

    public Optional<MySqlAviaryEntity> getAviaryById(Long id) {
        var aviary = aviaryRepository.findById(id);
        return aviary;
    }

    public AviaryDTO updateAviary(Long id, AviaryDTO aviaryDTO) {
        Optional<MySqlAviaryEntity> optionalAviary = aviaryRepository.findById(id);

        if (optionalAviary.isPresent()) {
            MySqlAviaryEntity existingAviary = optionalAviary.get();

            existingAviary.setName(aviaryDTO.getName());
            existingAviary.setBatchId(batchService.findBatchOrThrow(aviaryDTO.getBatchId()));


            MySqlAviaryEntity updatedAviary = aviaryRepository.save(existingAviary);

            return toAviaryDTO(updatedAviary);
        } else {
            throw new RuntimeException("Aviario não encontrado com o ID: " + id);
        }
    }
}
