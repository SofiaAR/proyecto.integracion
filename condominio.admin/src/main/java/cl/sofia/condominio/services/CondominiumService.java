package cl.sofia.condominio.services;


import cl.sofia.condominio.model.dtos.CondominiumDto;

import java.util.List;

public interface CondominiumService {

    CondominiumDto findByName(String condoName);

    List<CondominiumDto> findAll();

    CondominiumDto save(CondominiumDto newCondo);

    void update (CondominiumDto updateCondo);

    void delete(Long id);
}
