package cl.sofia.condominio.services;

import cl.sofia.condominio.model.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> findAllByTowerId(Long towerId);
}
