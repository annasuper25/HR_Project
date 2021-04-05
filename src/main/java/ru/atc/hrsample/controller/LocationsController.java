package ru.atc.hrsample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.service.api.LocationsService;

import java.util.List;

/**
 * РЕСТ контроллер для работы с локациями
 */
@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@Tag(name = "location", description = "АПИ для локаций")
public class LocationsController {

    private final LocationsService locationsService;

    @GetMapping("/{locationId}")
    @Operation(summary = "Получение локации по идентификатору")
    public LocationsDto getLocationById(@PathVariable Integer locationId) {
        return locationsService.getLocationById(locationId);
    }

    @PostMapping
    @Operation(summary = "Создание новой локации")
    public LocationsDto insertLocation(@RequestBody LocationsDto dto) {
        return locationsService.insertLocation(dto);
    }

    @GetMapping
    @Operation(summary = "Получение всех локаций")
    public List<LocationsDto> getAll() {
        return locationsService.getAll();
    }
}
