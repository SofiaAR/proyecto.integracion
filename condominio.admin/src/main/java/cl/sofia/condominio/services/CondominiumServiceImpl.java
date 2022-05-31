package cl.sofia.condominio.services;

import cl.sofia.condominio.model.dtos.CondominiumDto;
import cl.sofia.condominio.model.entities.Department;
import cl.sofia.condominio.model.entities.Tower;
import cl.sofia.condominio.model.repositories.CondominiumRepository;
import cl.sofia.condominio.model.repositories.DepartmentRepository;
import cl.sofia.condominio.model.repositories.TowerRepository;
import cl.sofia.condominio.services.mapper.CondominiumMapperDto;
import cl.sofia.condominio.model.entities.Condominium;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CondominiumServiceImpl implements CondominiumService {

    private final TowerRepository towerRepository;
    private final CondominiumRepository condominiumRepository;
    private final DepartmentRepository departmentRepository;

    public CondominiumServiceImpl(TowerRepository towerRepository,
                                  CondominiumRepository condominiumRepository,
                                  DepartmentRepository departmentRepository) {
        this.towerRepository = towerRepository;
        this.condominiumRepository = condominiumRepository;
        this.departmentRepository = departmentRepository;
    }

    //

    @Override
    public CondominiumDto findByName(String condoName) {
        Condominium condominiumEntity = condominiumRepository.findByName(condoName);
        return CondominiumMapperDto.toDto(condominiumEntity);
    }

    @Override
    public List<CondominiumDto> findAll() {
        List<CondominiumDto> condominiumDtoList = new ArrayList<>(); // crea lista vacia de tipo CondominiumDto
        List<Condominium> condominiums = condominiumRepository.findAll(); // obtiene los condominios de la base de datos

        for (Condominium condominium : condominiums) {//Recorre la lista
            CondominiumDto condominiumDto = CondominiumMapperDto.toDto(condominium);// Se transforman
            condominiumDtoList.add(condominiumDto);// se agregan a lista de condominiodto
        }

        return condominiumDtoList;
    }

    @Override
    @Transactional
    public CondominiumDto save(CondominiumDto condominiumDto) {
        Condominium condominium = new Condominium();
        condominium.setName(condominiumDto.getName());
        condominium.setAddress(condominiumDto.getAddress());
        condominiumRepository.save(condominium);
        condominiumDto.getTowers().forEach(towerDto -> {
            Tower tower = new Tower();
            tower.setIdentification(towerDto.getIdentification());
            tower.setCondominium(condominium);
            towerRepository.save(tower);
            towerDto.getDepartments().forEach(departmentDto -> {
                Department department = new Department();
                department.setNumber(departmentDto.getNumber());
                department.setTower(tower);
                departmentRepository.save(department);
            });
        });
        return CondominiumMapperDto.toDto(condominium);
    }

    @Override
    public void update(CondominiumDto updateCondo) {
        Condominium condominium = condominiumRepository.getById(updateCondo.getId());
        condominium.setName(updateCondo.getName());
        condominiumRepository.save(condominium);

    }

    @Override
    public void delete(Long id) {
        Condominium condominium = condominiumRepository.findById(id).orElseThrow();
        condominium.getTowers().forEach(tower -> {

        });
        condominiumRepository.delete(condominiumRepository.getById(id));
    }

}
