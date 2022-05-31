package cl.sofia.condominio.services.mapper;

import cl.sofia.condominio.model.dtos.DepartmentDto;
import cl.sofia.condominio.model.entities.Department;

public class DepartmentMapperDto {

    public static DepartmentDto toDto (Department department){
        if (department == null)
            return null;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setNumber(department.getNumber());
        return departmentDto;

    }
}
