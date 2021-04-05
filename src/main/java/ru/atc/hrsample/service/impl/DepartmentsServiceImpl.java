package ru.atc.hrsample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.atc.hrsample.dao.DepartmentsMapper;
import ru.atc.hrsample.dao.EmployeesMapper;
import ru.atc.hrsample.dao.LocationsMapper;
import ru.atc.hrsample.dto.DepartmentsDto;
import ru.atc.hrsample.dto.EmployeesDto;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.entity.DepartmentsEntity;
import ru.atc.hrsample.entity.EmployeesEntity;
import ru.atc.hrsample.entity.LocationsEntity;
import ru.atc.hrsample.service.api.DepartmentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentsServiceImpl implements DepartmentsService {

    private final ModelMapper modelMapper;
    private final DepartmentsMapper departmentsMapper;
    private final LocationsMapper locationsMapper;
    private final EmployeesMapper employeesMapper;


    @Override
    @Transactional(readOnly = true)
    public DepartmentsDto getDepartmentById(Integer id) {
        DepartmentsEntity departmentsEntity = departmentsMapper.getDepartmentById(id)
                .orElseThrow(() -> new RuntimeException(
                String.format("Не найдена сущность департамента по идентификатору=%s", id)
        ));
        log.debug("Получена сущность департамента с id={}", id);
        DepartmentsDto departmentsDto = modelMapper.map(departmentsEntity, DepartmentsDto.class);

        //Добавляем в объект информацию о локации:
        LocationsEntity locationsEntity = locationsMapper.getLocationById(departmentsEntity.getLocationId())
                .orElseThrow(() -> new RuntimeException(
                String.format("Не найдена сущность локации по идентификатору=%s", departmentsEntity.getLocationId())
        ));
        LocationsDto ldto = modelMapper.map(locationsEntity, LocationsDto.class);

        departmentsDto.setLocation(ldto);

        //Добавляем в объект информацию о менеджере:
        Integer idManager = departmentsEntity.getManagerId();
        EmployeesEntity employeesEntity = employeesMapper.getEmployeeById(idManager)
                .orElseThrow(() -> new RuntimeException(
                String.format("Не найдена сущность работника по идентификатору=%s", idManager)
        ));

        EmployeesDto employeesDto = modelMapper.map(employeesEntity, EmployeesDto.class);

        departmentsDto.setManagerFirstName(employeesDto.getFirstName());
        departmentsDto.setManagerLastName(employeesDto.getLastName());

        return departmentsDto;
    }


    @Override
    @Transactional
    public DepartmentsDto insertDepartment(DepartmentsDto dto) {
        DepartmentsEntity departmentsEntity = modelMapper.map(dto, DepartmentsEntity.class);
        departmentsMapper.insert(departmentsEntity);
        log.info("Добавлена сущность департамента с id={}", departmentsEntity.getDepartmentId());

        return getDepartmentById(departmentsEntity.getDepartmentId());
    }


    @Override
    @Transactional(readOnly = true)
    public List<DepartmentsDto> getAll() {
        List<DepartmentsEntity> listEntities = departmentsMapper.getAll();
        log.debug("Получены все сущности департаментов");
        return modelMapper.map(listEntities, TypeToken.of(List.class).getType());
    }


    @Transactional(readOnly = true)
    public List<DepartmentsDto> getAllWithFilter(String departmentName, String managerLastName) {

        List<DepartmentsEntity> listEntities = null;

        if(StringUtils.isEmpty(departmentName)) {
            if (StringUtils.isEmpty(managerLastName)) {
                listEntities = departmentsMapper.getAll();
                log.debug("Получены все сущности департаментов");
            }
            else
            {
                listEntities = departmentsMapper.getAllFilteredByManagerLastName(managerLastName);
                log.debug("Получены сущности департаментов, отфильтрованные по Фамилии менеджера");
            }
        }
        else {
            if(StringUtils.isEmpty(managerLastName)) {
                listEntities = departmentsMapper.getAllFilteredByDeparmentName(departmentName);
                log.debug("Получены сущности департаментов, отфильтрованные по Названию департамента");
            }
            else {
                listEntities = departmentsMapper.getAllFilteredByManagerAndDepName(departmentName, managerLastName);
                log.debug("Получены сущности департаментов, отфильтрованные по Названию департамента и Фамилии менеджера");
            }
        }

        return modelMapper.map(listEntities, TypeToken.of(List.class).getType());
    }


    @Override
    public void updateDepartment(DepartmentsDto departmentsDto) {

        DepartmentsEntity departmentsEntity = modelMapper.map(departmentsDto, DepartmentsEntity.class);
        departmentsMapper.updateDepartment(departmentsEntity);
        log.debug("Обновлена сущность департамента с id={}", departmentsEntity.getDepartmentId());
    }

}
