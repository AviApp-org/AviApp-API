package br.com.aviapp.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.usecases.employee.CreateEmployeeUseCase;
import br.com.aviapp.api.application.usecases.employee.DeleteEmployeeUseCase;
import br.com.aviapp.api.application.usecases.employee.FindEmployeeByIdUseCase;
import br.com.aviapp.api.application.usecases.employee.ListAllEmployeesUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployee;
    private final DeleteEmployeeUseCase deleteEmployee;
    private final FindEmployeeByIdUseCase findEmployeeById;
    private final ListAllEmployeesUseCase listAllEmployees;

    public EmployeeController(CreateEmployeeUseCase createEmployee, DeleteEmployeeUseCase deleteEmployee,
            FindEmployeeByIdUseCase findEmployeeById, ListAllEmployeesUseCase listAllEmployees
            ) {
        this.createEmployee = createEmployee;
        this.deleteEmployee = deleteEmployee;
        this.findEmployeeById = findEmployeeById;
        this.listAllEmployees = listAllEmployees;
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

}
