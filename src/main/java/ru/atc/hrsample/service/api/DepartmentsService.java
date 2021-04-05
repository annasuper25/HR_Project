package ru.atc.hrsample.service.api;

import ru.atc.hrsample.dto.DepartmentsDto;
import ru.atc.hrsample.dto.DepartmentsUpDto;
import ru.atc.hrsample.dto.LocationsDto;

import java.util.List;

/**
 * Сервис для работы с департаментами
 */
public interface DepartmentsService {

    /**
     * Получает сущность департамента по ид
     *
     * @param id идентификатор
     * @return сохраненная сущность с ид.
     */
    DepartmentsDto getDepartmentById(Integer id);

    /**
     * Сохраняет сущность департамента
     *
     * @param dto сущность департамента
     * @return сохраненная сущность с ид.
     */
    DepartmentsDto insertDepartment(DepartmentsDto dto);


    /**
     * Получает все департаменты
     * @return список всех департаментов
     */
    List<DepartmentsDto> getAll();


    /**
     * Получает все департаменты с применением фильтра
     * @return список департаментов
     */
    List<DepartmentsDto> getAllWithFilter(String departmentName, String managerLastName);


    /**
     * Обновляет сущность департамента
     * @param departmentsDto сущность
     */
    void updateDepartment(DepartmentsDto departmentsDto);
}
