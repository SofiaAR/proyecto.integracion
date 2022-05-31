package cl.sofia.condominio.model.repositories;


import cl.sofia.condominio.model.entities.Tower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TowerRepository extends JpaRepository<Tower,Long> {

    List<Tower> findAllByCondominiumId(Long condominioId);

}
