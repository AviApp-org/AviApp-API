package br.com.aviapp.api.presentation.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.domain.dto.EmployeeTypeDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeType;
import br.com.aviapp.api.services.EmployeeTypeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employee/type")
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeService employeeTypeService;

    @GetMapping
    public ResponseEntity<List<EmployeeTypeDTO>> getAllEmployeeTypes() {
        List<EmployeeTypeDTO> employeeTypes = employeeTypeService.listEmployeeTypes();
        return ResponseEntity.ok(employeeTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeTypeDTO> findById(@PathVariable Long id) {
        Optional<MySqlEmployeeType> employeeTypeOptional = employeeTypeService.getEmployeeTypeById(id);
        if (employeeTypeOptional.isPresent()) {
            MySqlEmployeeType employeeType = employeeTypeOptional.get();
            EmployeeTypeDTO employeeTypeDTO = employeeTypeService.toEmployeeTypeDTO(employeeType);
            return ResponseEntity.ok(employeeTypeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeTypeDTO> createEmployeeType(@RequestBody EmployeeTypeDTO employeeTypeDTO) {
        Long createdEmployeeTypeId = employeeTypeService.save(employeeTypeDTO);
        URI location = URI.create("/api/EmployeeTypes/" + createdEmployeeTypeId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeTypeDTO> updateEmployeeType(@PathVariable Long id, @RequestBody EmployeeTypeDTO employeeTypeDTO) {
        EmployeeTypeDTO updatedEmployeeType = employeeTypeService.updateEmployeeType(id, employeeTypeDTO);
        return ResponseEntity.ok(updatedEmployeeType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeType(@PathVariable Long id) {
        employeeTypeService.deleteEmployeeType(id);
        return ResponseEntity.noContent().build();
    }
}
