package cl.sofia.condominio.model.repositories;


import cl.sofia.condominio.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByTowerId(Long id);

}

