package cl.sofia.condominio.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identification;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;
    @OneToMany(mappedBy = "tower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
