package ru.atc.hrsample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsEntity {
    private String jobId;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;
}
