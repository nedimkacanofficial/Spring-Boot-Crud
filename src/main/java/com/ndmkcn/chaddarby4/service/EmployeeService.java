package com.ndmkcn.chaddarby4.service;

import com.ndmkcn.chaddarby4.dto.EmployeeDTO;
import com.ndmkcn.chaddarby4.entity.Employee;
import com.ndmkcn.chaddarby4.exception.NotFoundException;
import com.ndmkcn.chaddarby4.mapper.EmployeeMapper;
import com.ndmkcn.chaddarby4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    public List<EmployeeDTO> findAll(){
        List<Employee> employees=employeeRepository.findAll();
        return employeeMapper.entityToDTOList(employees);
    }
    public EmployeeDTO findById(Long id){
        Optional<Employee> employee=employeeRepository.findById(id);
        if (employee.isPresent()){
            Employee employee1=employee.get();
            return employeeMapper.entityToDTO(employee1);
        } else {
            throw new NotFoundException("Belirtilen Id değerine sahip çalışan bulunamadı ! - " + id);
        }
    }
    public EmployeeDTO save(EmployeeDTO employeeDTO){
        Employee employee=employeeMapper.dtoToEntity(employeeDTO);
        Employee savedEmployee=employeeRepository.save(employee);
        return employeeMapper.entityToDTO(savedEmployee);
    }
    public EmployeeDTO update(EmployeeDTO employeeDTO,Long id){
        Optional<Employee> optionalEmployee=employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            Employee updateEmployee=employeeRepository.save(employee);
            return employeeMapper.entityToDTO(updateEmployee);
        } else {
            throw new NotFoundException("Belirtilen Id değerine sahip çalışan bulunamadı ! - " + id);
        }
    }
    public String deleteById(Long id){
        employeeRepository.deleteById(id);
        return "Kayıt başarılı bir şekilde silindi ! - " + id;
    }
}
