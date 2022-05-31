package cl.sofia.condominio.services.mapper;


import cl.sofia.condominio.model.dtos.TowerDto;
import cl.sofia.condominio.model.entities.Tower;

public class TowerMapperDto {
    public static TowerDto toDto(Tower tower) {
        if (tower == null)
            return null;
        TowerDto towerDto = new TowerDto();
        towerDto.setId(tower.getId());
        towerDto.setIdentification(tower.getIdentification());
        tower.getDepartments().forEach(department -> towerDto.getDepartments().add(DepartmentMapperDto.toDto(department)));
        return towerDto;
    }
}
