package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.EmployeeTypeDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeType;
import br.com.aviapp.api.infra.mysql.repository.EmployeeTypeRepository;

@Service
public class EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    public List<MySqlEmployeeType> findAll() {
        return employeeTypeRepository.findAll();
    }

    public EmployeeTypeDTO findById(Long id) {
        Optional<MySqlEmployeeType> employeeTypeOptional = employeeTypeRepository.findById(id);
        if (employeeTypeOptional.isPresent()) {
            MySqlEmployeeType employeeType = employeeTypeOptional.get();
            return toEmployeeTypeDTO(employeeType);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    public Long save(EmployeeTypeDTO employeeTypeDTO) {

        var entity = new MySqlEmployeeType(
            null,
            employeeTypeDTO.getDescription()
        );
        
        var savedEmployeeType = employeeTypeRepository.save(entity);
        return savedEmployeeType.getId();
    }

    public EmployeeTypeDTO toEmployeeTypeDTO(MySqlEmployeeType mySqlEmployeeType) {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setDescription(mySqlEmployeeType.getDescription());
        // Mapear outros campos, se necessário
        return employeeTypeDTO;
    }

    public void deleteEmployeeType(Long id) {
        var employeeTypeExiste = employeeTypeRepository.existsById(id);

        if (employeeTypeExiste) {
            employeeTypeRepository.deleteById(id);
        }
    }

    public List<EmployeeTypeDTO> listEmployeeTypes() {
        List<MySqlEmployeeType> employeeTypes = employeeTypeRepository.findAll();
        return employeeTypes.stream()
                .map(this::toEmployeeTypeDTO)
                .collect(Collectors.toList());
    }

    public Optional<MySqlEmployeeType> getEmployeeTypeById(Long id) {
        var employeeType = employeeTypeRepository.findById(id);
        return employeeType;
    }

    public EmployeeTypeDTO updateEmployeeType(Long id, EmployeeTypeDTO employeeTypeDTO) {
        Optional<MySqlEmployeeType> optionalEmployeeType = employeeTypeRepository.findById(id);

        if (optionalEmployeeType.isPresent()) {
            MySqlEmployeeType existingEmployeeType = optionalEmployeeType.get();

            existingEmployeeType.setDescription(employeeTypeDTO.getDescription());
            

            MySqlEmployeeType updatedEmployeeType = employeeTypeRepository.save(existingEmployeeType);

            return toEmployeeTypeDTO(updatedEmployeeType);
        } else {
            throw new RuntimeException("Tipo de funcionario não encontrado com o ID: " + id);
        }
    }
}
