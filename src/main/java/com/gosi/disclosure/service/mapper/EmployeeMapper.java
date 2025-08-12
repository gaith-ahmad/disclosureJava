package com.gosi.disclosure.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.gosi.disclosure.domain.Employee;
import com.gosi.disclosure.service.dto.EmployeeDTO;

public class EmployeeMapper {

    public static EmployeeDTO toDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setDisplayName(entity.getDisplayName());
        dto.setEmail(entity.getEmail());
        dto.setTitle(entity.getTitle());
        dto.setTelephoneNumber(entity.getTelephoneNumber());
        dto.setLocalPhone(entity.getLocalPhone());
        dto.setDepartment(entity.getDepartment());
        dto.setPhysicalDeliveryOfficeName(entity.getPhysicalDeliveryOfficeName());
        dto.setDistinguishedName(entity.getDistinguishedName());
        dto.setUsername(entity.getUsername());

        return dto;
    }

    public static List<EmployeeDTO> toDtoList(List<Employee> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
