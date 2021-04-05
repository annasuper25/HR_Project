package ru.atc.hrsample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.atc.hrsample.dao.LocationsMapper;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.entity.LocationsEntity;
import ru.atc.hrsample.service.api.LocationsService;

import java.util.List;

/**
 * Реализация сервиса по работе с локациями
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LocationsServiceImpl implements LocationsService {

    private final LocationsMapper locationsMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public LocationsDto getLocationById(Integer id) {
        LocationsEntity locationsEntity = locationsMapper.getLocationById(id).orElseThrow(
                () -> new RuntimeException(
                        String.format("Не найдена сущность локации по идентификатору=%s", id)
                )
        );
        log.debug("Получена сущность локации с id={}", id);
        return modelMapper.map(locationsEntity, LocationsDto.class);
    }

    @Override
    @Transactional
    public LocationsDto insertLocation(LocationsDto dto) {
        LocationsEntity locationsEntity = modelMapper.map(dto, LocationsEntity.class);
        locationsMapper.insert(locationsEntity);
        log.info("Создана сущность с id={}", locationsEntity.getLocationId());
        return getLocationById(locationsEntity.getLocationId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationsDto> getAll() {
        List<LocationsEntity> entities = locationsMapper.getAll();
        log.debug("Получены все сущности локаций");
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }
}
