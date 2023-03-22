package com.ndmkcn.chaddarby4.controller;

import com.ndmkcn.chaddarby4.dto.EmployeeDTO;
import com.ndmkcn.chaddarby4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/employees")
    public List<EmployeeDTO> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{id}")
    public EmployeeDTO findById(@PathVariable Long id){
        return employeeService.findById(id);
    }
    @PostMapping("/employees")
    public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.save(employeeDTO);
    }
    @PutMapping("/employees/{id}")
    public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long id){
        return employeeService.update(employeeDTO,id);
    }
    @DeleteMapping("/employees/{id}")
    public String deleteById(@PathVariable Long id){
        return employeeService.deleteById(id);
    }
}
