package ru.atc.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель департамента
 */
@Data
@Schema(title = "Модель данных департамента")
public class DepartmentsUpDto {

    @Schema(title = "Идентификатор департамента")
    private Integer departmentId;

    @Schema(title = "Название департамента", required = true)
    private String departmentName;

    @Schema(title = "Идентификатор главы департамента")
    private Integer managerId;

    @Schema(title = "Идентификатор локации")
    private Integer locationId;

}
