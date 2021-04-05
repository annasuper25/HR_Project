package ru.atc.hrsample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.atc.hrsample.dto.DepartmentsDto;
import ru.atc.hrsample.dto.EmployeesDto;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.service.api.EmployeesService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "employee", description = "АПИ для сотрудников")
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping("/{employeeId}")
    @Operation(summary = "Получение сотрудника по идентификатору")
    public EmployeesDto getEmployeeById(@PathVariable Integer employeeId){
       return employeesService.getEmployeeById(employeeId);
    }

    @GetMapping
    @Operation(summary = "Получение всех сотрудников")
    public List<EmployeesDto> getAll(@RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate) {
        return employeesService.getAllWithFilter(firstName, lastName, email, startDate, endDate);
    }
}
