package cl.sofia.condominio.web;

//REST

import cl.sofia.condominio.model.dtos.CondominiumDto;
import cl.sofia.condominio.services.CondominiumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("condominiums")
public class CondominiumController {

    private final CondominiumService condominiumService;

    public CondominiumController(CondominiumService condominiumService){
        this.condominiumService = condominiumService;}


    @GetMapping("{condoName}")
        public ResponseEntity<?> findByName (@PathVariable String condoName){
            return ResponseEntity.ok(condominiumService.findByName(condoName));
    }


    @GetMapping()
    public ResponseEntity <?> findAll(){return ResponseEntity.ok(condominiumService.findAll());}

    @DeleteMapping ("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        condominiumService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<?> create (@RequestBody CondominiumDto condominiumDto){
        condominiumService.save(condominiumDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<?> update (@RequestBody CondominiumDto condominiumDto){
        condominiumService.save(condominiumDto);
        return ResponseEntity.ok().build();

    }
}
