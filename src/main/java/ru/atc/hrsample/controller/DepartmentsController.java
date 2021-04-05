package ru.atc.hrsample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.atc.hrsample.dto.DepartmentsDto;
import ru.atc.hrsample.dto.DepartmentsUpDto;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.service.api.DepartmentsService;

import java.util.List;

/**
 * РЕСТ контроллер для работы с департаментами
 */
@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Tag(name = "departments", description = "АПИ для департаментов")
public class DepartmentsController {

    private final DepartmentsService departmentsService;

    @GetMapping("/{departmentId}")
    @Operation(summary = "Получение департамента по идентификатору")
    public DepartmentsDto getDepartmentById(@PathVariable Integer departmentId){
        return departmentsService.getDepartmentById(departmentId);
    }

    @PostMapping
    @Operation(summary = "Создание нового департамента")
    public DepartmentsDto insertDepartment(@RequestBody DepartmentsDto dto) {
        return departmentsService.insertDepartment(dto);
    }

    @GetMapping
    @Operation(summary = "Получение всех департаментов")
    public List<DepartmentsDto> getAll(@RequestParam(required = false) String departmentName,
                                       @RequestParam(required = false) String managerLastName) {
        return departmentsService.getAllWithFilter(departmentName, managerLastName);
    }

    @PutMapping()
    @Operation(summary = "Обновление данных департамента")
    public void updateDepartmentById(@RequestBody DepartmentsDto departmentDto){
         departmentsService.updateDepartment(departmentDto);
    }
}
