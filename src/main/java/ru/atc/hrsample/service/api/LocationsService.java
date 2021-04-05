package ru.atc.hrsample.service.api;

import ru.atc.hrsample.dto.LocationsDto;

import java.util.List;

/**
 * Сервис для работы с локациями
 */
public interface LocationsService {

    /**
     * Получает сущность локации по ид
     *
     * @param id идентификатор
     * @return сохраненная сущность с ид.
     */
    LocationsDto getLocationById(Integer id);

    /**
     * Сохраняет сущность локации
     *
     * @param dto сущность локации
     * @return сохраненная сущность с ид.
     */
    LocationsDto insertLocation(LocationsDto dto);


    /**
     * Получает все локации
     * @return список всех локаций
     */
    List<LocationsDto> getAll();
}
