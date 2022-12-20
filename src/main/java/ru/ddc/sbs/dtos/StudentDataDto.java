package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO \"Студент, курс, рейтинг и статус зачета курса\"")
public class StudentDataDto {
    private StudentDto studentDto;
    private CourseDto courseDto;
    @Schema(description = "Рейтинг")
    private Double rating;
    @Schema(description = "Статус зачета курса")
    private Boolean isCredited;
}
