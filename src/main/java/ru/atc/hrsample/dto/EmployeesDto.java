package ru.atc.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.atc.hrsample.entity.DepartmentsEntity;
import ru.atc.hrsample.entity.EmployeesEntity;
import ru.atc.hrsample.entity.JobsEntity;

import java.time.LocalDate;

/**
 * Модель работника
 */
@Data
@Schema(title = "Модель данных работника")
public class EmployeesDto {

    @Schema(title = "Идентификатор работника")
    private Integer employeeId;

    @Schema(title = "Имя работника")
    private String firstName;

    @Schema(title = "Фамилия работника")
    private String lastName;

    @Schema(title = "Е-мейл работника")
    private String email;

    @Schema(title = "Телефон работника")
    private String phoneNumber;

    @Schema(title = "Дата устройства на работу работника")
    private LocalDate hireDate;

    @Schema(title = "Идентификатор должности работника")
    private String jobId;

    @Schema(title = "Должность работника")
    private JobsEntity jobs;

    @Schema(title = "Зарплата работника")
    private Integer salary;

    @Schema(title = "Идентификатор руководителя работника")
    private Integer managerId;

    @Schema(title = "Руководитель работника")
    private EmployeesEntity manager;

    @Schema(title = "Идентификатор департамента работника")
    private Integer departmentId;

    @Schema(title = "Департамент работника")
    private DepartmentsEntity department;
}
