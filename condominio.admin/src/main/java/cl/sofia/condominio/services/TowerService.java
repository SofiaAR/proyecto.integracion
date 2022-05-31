package cl.sofia.condominio.services;

import cl.sofia.condominio.model.dtos.TowerDto;

import java.util.List;

public interface TowerService {

    List<TowerDto> findAllByCondominioId(Long condominioId);
}
