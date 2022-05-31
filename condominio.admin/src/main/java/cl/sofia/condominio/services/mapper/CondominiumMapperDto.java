package cl.sofia.condominio.services.mapper;

import cl.sofia.condominio.model.dtos.CondominiumDto;
import cl.sofia.condominio.model.entities.Condominium;

public class CondominiumMapperDto {

    public static CondominiumDto toDto (Condominium condominium){
        if (condominium == null)
            return null;
        CondominiumDto condominiumDto = new CondominiumDto();
        condominiumDto.setAddress(condominium.getAddress());
        condominiumDto.setId(condominium.getId());
        condominiumDto.setName(condominium.getName());
        condominium.getTowers().forEach(tower -> condominiumDto.getTowers().add(TowerMapperDto.toDto(tower)));
        return condominiumDto;
    }


}
