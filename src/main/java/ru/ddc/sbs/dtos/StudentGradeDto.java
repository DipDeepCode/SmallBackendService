package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO \"Студент, курс, задание и соответствующая оценка\"")
public class StudentGradeDto {
    @Schema(description = "Студент")
    private StudentDto studentDto;
    @Schema(description = "Курс")
    private CourseDto courseDto;
    @Schema(description = "Задание")
    private TaskDto taskDto;
    @Schema(description = "Оценка")
    private Integer grade;
}
