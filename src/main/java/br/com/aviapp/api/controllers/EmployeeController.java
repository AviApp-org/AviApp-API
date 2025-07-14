package br.com.aviapp.api.controllers;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.usecases.employee.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployee;
    private final DeleteEmployeeUseCase deleteEmployee;
    private final FindEmployeeByIdUseCase findEmployeeById;
    private final ListAllEmployeesUseCase listAllEmployees;
    private final UpdateEmployeeUseCase updateEmployee;
    private final GetEmployeesByFarmIdUseCase getEmployeesByFarmIdUseCase;

    public EmployeeController(CreateEmployeeUseCase createEmployee, DeleteEmployeeUseCase deleteEmployee,
                              FindEmployeeByIdUseCase findEmployeeById, ListAllEmployeesUseCase listAllEmployees, UpdateEmployeeUseCase updateEmployee, GetEmployeesByFarmIdUseCase getEmployeesByFarmIdUseCase) {
        this.createEmployee = createEmployee;
        this.deleteEmployee = deleteEmployee;
        this.findEmployeeById = findEmployeeById;
        this.listAllEmployees = listAllEmployees;
        this.updateEmployee = updateEmployee;
        this.getEmployeesByFarmIdUseCase = getEmployeesByFarmIdUseCase;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listAllEmployees() {
        List<EmployeeDTO> employees = listAllEmployees.invoke();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employee = findEmployeeById.invoke(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByFarm(@PathVariable Long farmId) {
        List<EmployeeDTO> employees = getEmployeesByFarmIdUseCase.invoke(farmId);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = createEmployee.invoke(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        deleteEmployee.invoke(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        Optional<EmployeeDTO> updatedEmployee = updateEmployee.invoke(id, employeeDTO);
        return updatedEmployee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
