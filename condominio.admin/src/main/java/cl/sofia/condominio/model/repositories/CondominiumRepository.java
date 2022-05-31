package cl.sofia.condominio.model.repositories;

import cl.sofia.condominio.model.entities.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominiumRepository extends JpaRepository<Condominium ,Long> {

    Condominium findByName(String name);

}
