package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGradeDto {
    private StudentDto studentDto;
    private CourseDto courseDto;
    private TaskDto taskDto;
    private Integer grade;
}
