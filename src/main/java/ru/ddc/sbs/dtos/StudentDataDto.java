package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDataDto {
    private StudentDto studentDto;
    private CourseDto courseDto;
    private Double rating;
    private Boolean isCredited;
}
