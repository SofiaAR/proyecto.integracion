package cl.sofia.condominio.web;

import cl.sofia.condominio.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("tower/{towerId}")
    public ResponseEntity<?> findByTowerId(@PathVariable Long towerId) {
        return ResponseEntity.ok(departmentService.findAllByTowerId(towerId));
    }
}
