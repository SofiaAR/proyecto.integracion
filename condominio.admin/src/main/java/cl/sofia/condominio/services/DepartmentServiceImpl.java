package cl.sofia.condominio.services;

import cl.sofia.condominio.model.dtos.DepartmentDto;
import cl.sofia.condominio.model.dtos.UserDto;
import cl.sofia.condominio.model.entities.Department;
import cl.sofia.condominio.model.repositories.DepartmentRepository;
import cl.sofia.condominio.services.mapper.DepartmentMapperDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final WebClient usersApi;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        this.usersApi = WebClient
                .builder()
                .baseUrl("http://localhost:8090/condominio")
                .defaultHeader("accept", "*/*").build();
    }

    @Override
    public List<DepartmentDto> findAllByTowerId(Long towerId) {
        List<DepartmentDto> departmentDtoList = new ArrayList<>();

        List<Department> departments = departmentRepository.findAllByTowerId(towerId);

        departments.forEach(department -> {
            DepartmentDto departmentDto = DepartmentMapperDto.toDto(department);

            if (department.getUserId() != null) {
                UserDto userDto = usersApi.get().uri("/users/id/" + department.getUserId()).retrieve().toEntity(UserDto.class).block().getBody();
                departmentDto.setUser(userDto);
            }
            departmentDtoList.add(departmentDto);
        });

        return departmentDtoList;
    }
}
