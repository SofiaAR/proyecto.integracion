package cl.sofia.condominio.web;

import cl.sofia.condominio.services.TowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("towers")
public class TowerController {

    private final TowerService towerService;

    public TowerController(TowerService towerService) {
        this.towerService = towerService;
    }

    @GetMapping("condominio/{condoId}")
    public ResponseEntity<?> findByCondominioId(@PathVariable Long condoId) {
        return ResponseEntity.ok(towerService.findAllByCondominioId(condoId));
    }

}
