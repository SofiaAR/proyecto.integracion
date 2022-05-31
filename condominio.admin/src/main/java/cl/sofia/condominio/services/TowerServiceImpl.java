package cl.sofia.condominio.services;

import cl.sofia.condominio.model.dtos.TowerDto;
import cl.sofia.condominio.model.repositories.TowerRepository;
import cl.sofia.condominio.services.TowerService;
import cl.sofia.condominio.services.mapper.TowerMapperDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TowerServiceImpl implements TowerService {

    private final TowerRepository towerRepository;

    public TowerServiceImpl(TowerRepository towerRepository) {
        this.towerRepository = towerRepository;
    }

    @Override
    public List<TowerDto> findAllByCondominioId(Long condominioId) {
        return towerRepository.findAllByCondominiumId(condominioId)
                .stream().map(TowerMapperDto::toDto)
                .collect(Collectors.toList());
    }
}
