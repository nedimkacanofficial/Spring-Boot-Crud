package com.ndmkcn.chaddarby4.mapper;

import com.ndmkcn.chaddarby4.dto.EmployeeDTO;
import com.ndmkcn.chaddarby4.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeDTO entityToDTO(Employee employee){
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        return employeeDTO;
    }
    public Employee dtoToEntity(EmployeeDTO employeeDTO){
        Employee employee=new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        return employee;
    }
    public List<EmployeeDTO> entityToDTOList(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();
        for (Employee employee:employees) {
            EmployeeDTO employeeDTO=new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
    public List<Employee> dtoToEntityList(List<EmployeeDTO> employeeDTOS){
        List<Employee> employees=new ArrayList<>();
        for (EmployeeDTO employeeDTO:employeeDTOS) {
            Employee employee=new Employee();
            employee.setId(employeeDTO.getId());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employees.add(employee);
        }
        return employees;
    }
}
