package ru.atc.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель департамента
 */
@Data
@Schema(title = "Модель данных департамента")
public class DepartmentsDto {

    @Schema(title = "Идентификатор департамента")
    private Integer departmentId;

    @Schema(title = "Название департамента", required = true)
    private String departmentName;

    @Schema(title = "Идентификатор главы департамента")
    private Integer managerId;

    @Schema(title = "Имя главы департамента")
    private String managerFirstName;

    @Schema(title = "Фамилия главы департамента")
    private String managerLastName;

    @Schema(title = "Id связанной локации")
    private Integer locationId;

    @Schema(title = "Связанная локация")
    private LocationsDto location;

}
