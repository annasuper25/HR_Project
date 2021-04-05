package ru.atc.hrsample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsEntity {

    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
