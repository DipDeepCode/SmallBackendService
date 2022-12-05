package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CourseDto implements Serializable {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}