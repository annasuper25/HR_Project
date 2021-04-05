package ru.atc.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель локации
 */
@Data
@Schema(title = "Модель данных локации")
public class LocationsDto {

    @Schema(title = "Идентификатор локации")
    private Integer locationId;

    @Schema(title = "Уличный адрес")
    private String streetAddress;

    @Schema(title = "город", required = true)
    private String city;
}
