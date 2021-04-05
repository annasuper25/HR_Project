package ru.atc.hrsample.service.api;

import ru.atc.hrsample.dto.DepartmentsDto;
import ru.atc.hrsample.dto.EmployeesDto;

import java.util.List;

/**
 * Сервис для работы с сотрудниками
 */
public interface EmployeesService {

    /**
     * Получает сущность сотрудника по id
     * @param id идентификатор сотрудника
     * @return сохраненная сущность с id
     */
    EmployeesDto getEmployeeById(Integer id);

    /**
     * Добавляет сущность сотрудника
     * @param dto сущность сотрудника
     * @return сохраненная сущность с id
     */
    EmployeesDto insertEmployee(EmployeesDto dto);

    /**
     * Получает всех сотрудников
     * @return
     */
    List<EmployeesDto> getAll();

    /**
     * Получает всех сотрудников с применением фильтра
     * @return список сотрудников
     */
    List<EmployeesDto> getAllWithFilter(String firstName, String lastName, String email, String startDate, String endDate);
}
