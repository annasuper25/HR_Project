package ru.atc.hrsample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationsEntity {
    private Integer locationId;
    private String streetAddress;
    private String city;
}
