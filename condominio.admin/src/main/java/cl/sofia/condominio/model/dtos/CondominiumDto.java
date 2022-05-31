package cl.sofia.condominio.model.dtos;


import java.util.ArrayList;
import java.util.List;

public class CondominiumDto {

    private Long id;
    private String name;
    private String address;
    private List<TowerDto> towers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<TowerDto> getTowers() {
        return towers;
    }

    public void setTowers(List<TowerDto> towers) {
        this.towers = towers;
    }
}

