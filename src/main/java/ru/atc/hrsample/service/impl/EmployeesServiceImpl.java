package ru.atc.hrsample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.atc.hrsample.dao.DepartmentsMapper;
import ru.atc.hrsample.dao.EmployeesMapper;
import ru.atc.hrsample.dao.JobsMapper;
import ru.atc.hrsample.dto.DepartmentsDto;
import ru.atc.hrsample.dto.EmployeesDto;
import ru.atc.hrsample.dto.JobsDto;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.entity.DepartmentsEntity;
import ru.atc.hrsample.entity.EmployeesEntity;
import ru.atc.hrsample.entity.JobsEntity;
import ru.atc.hrsample.entity.LocationsEntity;
import ru.atc.hrsample.service.api.EmployeesService;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


/**
 * Реализация сервиса по работе с работниками
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final ModelMapper modelMapper;
    private final EmployeesMapper employeesMapper;
    private final JobsMapper jobsMapper;
    private final DepartmentsMapper departmentsMapper;

    @Override
    @Transactional(readOnly = true)
    public EmployeesDto getEmployeeById(Integer id) {
        EmployeesEntity employeesEntity = employeesMapper.getEmployeeById(id).orElseThrow(
                () -> new RuntimeException(String.format("Не найдена сущность работника по идентификатору=%s", id))
        );
        log.debug("Получена сущность работника с id={}", id);

        EmployeesDto employeesDto = modelMapper.map(employeesEntity, EmployeesDto.class);

        //Добавляем в объект информацию о должности:
        JobsEntity jobsEntity = jobsMapper.getJobById(employeesEntity.getJobId()).orElseThrow(() -> new RuntimeException(
                String.format("Не найдена сущность должности по идентификатору=%s", employeesEntity.getJobId())
        ));
        employeesDto.setJobs(jobsEntity);


        //Добавляем в объект информацию о менеджере:
        Integer idManager = employeesEntity.getManagerId();
        EmployeesEntity managerEntity = employeesMapper.getEmployeeById(idManager).orElseThrow(() -> new RuntimeException(
                String.format("Не найдена сущность менеджера по идентификатору=%s", idManager)
        ));
        employeesDto.setManager(managerEntity);

        //Добавляем в объект информацию о департаменте:
        Integer idDepartment = employeesEntity.getDepartmentId();
        DepartmentsEntity departmentsEntity = departmentsMapper.getDepartmentById(idDepartment).orElseThrow(() -> new RuntimeException(
                String.format("Не найдена сущность департамента по идентификатору=%s", idManager)
        ));
        employeesDto.setDepartment(departmentsEntity);

        return employeesDto;
    }


    @Override
    @Transactional
    public EmployeesDto insertEmployee(EmployeesDto dto) {
        EmployeesEntity employeesEntity = modelMapper.map(dto, EmployeesEntity.class);
        employeesMapper.insert(employeesEntity);
        log.info("Создана сущность работника с id={}", employeesEntity.getEmployeeId());
        return getEmployeeById(employeesEntity.getEmployeeId());
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeesDto> getAll() {
        List<EmployeesEntity> employeesEntities = employeesMapper.getAll();
        log.debug("Получены все сущности работников");

        return modelMapper.map(employeesEntities, TypeToken.of(List.class).getType());
    }

    @Override
    public List<EmployeesDto> getAllWithFilter(String firstName, String lastName, String email, String startDate, String endDate) {

        List<EmployeesEntity> employeesEntities = employeesMapper.getAll();
        log.debug("Получены все сущности работников");

        //Фильтруем список сотрудников, если введен фильтр по имени
        if(!StringUtils.isEmpty(firstName)) {
            Predicate<EmployeesEntity> FirstNameIsNotEquals = employee -> !employee.getFirstName().equalsIgnoreCase(firstName);
            employeesEntities.removeIf(FirstNameIsNotEquals);
            log.debug("Сущности работников отфильтрованы по имени");
        }

        //Фильтруем список сотрудников, если введен фильтр по фамилии
        if(!StringUtils.isEmpty(lastName)) {
            Predicate<EmployeesEntity> LastNameIsNotEquals = employee -> !employee.getLastName().equalsIgnoreCase(lastName);
            employeesEntities.removeIf(LastNameIsNotEquals);
            log.debug("Сущности работников отфильтрованы по фамилии");
        }

        //Фильтруем список сотрудников, если введен фильтр по е-мэйл
        if(!StringUtils.isEmpty(email)) {
            Predicate<EmployeesEntity> emailIsNotEquals = employee -> !employee.getEmail().equalsIgnoreCase(email);
            employeesEntities.removeIf(emailIsNotEquals);
            log.debug("Сущности работников отфильтрованы по е-мэйл");
        }

        //Фильтруем список сотрудников, если введен фильтр по дате с
        if(!StringUtils.isEmpty(startDate)) {
            LocalDate localStartDate = LocalDate.parse(startDate);

            Predicate<EmployeesEntity> HireDateIsNotAfterStartDate = employee -> !employee.getHireDate().isAfter(localStartDate);
            employeesEntities.removeIf(HireDateIsNotAfterStartDate);
            log.debug("Сущности работников отфильтрованы по дате устройства с...");
        }

        //Фильтруем список сотрудников, если введен фильтр по дате по
        if(!StringUtils.isEmpty(endDate)) {
            LocalDate localEndDate = LocalDate.parse(endDate);

            Predicate<EmployeesEntity> HireDateIsNotBeforeEndDate = employee -> !employee.getHireDate().isBefore(localEndDate);
            employeesEntities.removeIf(HireDateIsNotBeforeEndDate);
            log.debug("Сущности работников отфильтрованы по дате устройства до...");
        }

        return modelMapper.map(employeesEntities, TypeToken.of(List.class).getType());
    }
}
