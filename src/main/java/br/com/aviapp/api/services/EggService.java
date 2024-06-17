package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.EggDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlEggEntity;
import br.com.aviapp.api.infra.mysql.repository.EggRepository;

@Service
public class EggService {
   
    @Autowired
    private EggRepository eggRepository;

    public List<MySqlEggEntity> findAll() {
        return eggRepository.findAll();
    }

    public EggDTO findById(Long id) {
        Optional<MySqlEggEntity> eggEntityOptional = eggRepository.findById(id);
        if (eggEntityOptional.isPresent()) {
            MySqlEggEntity eggEntity = eggEntityOptional.get();
            return toEggDTO(eggEntity);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    public Long save(EggDTO eggDTO) {

        var entity = new MySqlEggEntity(
            null,
            eggDTO.getType()
        );
        
        var savedEgg = eggRepository.save(entity);
        return savedEgg.getId();
    }

    public EggDTO toEggDTO(MySqlEggEntity mySqlEggEntity) {
        EggDTO eggDTO = new EggDTO();
        eggDTO.setType(mySqlEggEntity.getType());
        // Mapear outros campos, se necessário
        return eggDTO;
    }

    public void deleteEgg(Long id) {
        var eggExiste = eggRepository.existsById(id);

        if (eggExiste) {
            eggRepository.deleteById(id);
        }
    }

    public List<EggDTO> listEggs() {
        List<MySqlEggEntity> eggs = eggRepository.findAll();
        return eggs.stream()
                .map(this::toEggDTO)
                .collect(Collectors.toList());
    }

    public Optional<MySqlEggEntity> getEggById(Long id) {
        var Egg = eggRepository.findById(id);
        return Egg;
    }

    public EggDTO updateEgg(Long id, EggDTO eggDTO) {
        Optional<MySqlEggEntity> optionalEgg = eggRepository.findById(id);

        if (optionalEgg.isPresent()) {
            MySqlEggEntity existingEgg = optionalEgg.get();

            existingEgg.setType(eggDTO.getType());
            

            MySqlEggEntity updatedEgg = eggRepository.save(existingEgg);

            return toEggDTO(updatedEgg);
        } else {
            throw new RuntimeException("Ovo não encontrado com o ID: " + id);
        }
    }
}
