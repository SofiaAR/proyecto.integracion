package cl.sofia.condominio.model.dtos;



public class DepartmentDto  {

    private Long id;
    private String number;
    private TowerDto tower;
    private UserDto user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TowerDto getTower() {
        return tower;
    }

    public void setTower(TowerDto tower) {
        this.tower = tower;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
