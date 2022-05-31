package cl.sofia.condominio.model.dtos;


import java.util.ArrayList;
import java.util.List;

public class TowerDto {

    private Long id;
    private String identification;
    private List<DepartmentDto> departments = new ArrayList<>();
    private CondominiumDto condominium;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CondominiumDto getCondominium() {
        return condominium;
    }

    public void setCondominium(CondominiumDto condominium) {
        this.condominium = condominium;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public List<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDto> departments) {
        this.departments = departments;
    }
}

