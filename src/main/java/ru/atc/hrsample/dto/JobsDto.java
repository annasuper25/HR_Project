package ru.atc.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель работы
 */
@Data
@Schema(title = "Модель данных работы")
public class JobsDto {

    @Schema(title = "Идентификатор работы")
    private String jobId;

    @Schema(title = "Название работы")
    private String jobTitle;

    @Schema(title = "Минимальная зарплата")
    private Integer minSalary;

    @Schema(title = "Максимальная зарплата")
    private Integer maxSalary;
}
