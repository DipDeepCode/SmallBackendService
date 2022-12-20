package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "DTO \"Задание, курс и дедлайн\"")
public class TaskDeadlineDto {
    @Schema(description = "Задание")
    private TaskDto task;
    @Schema(description = "Курс")
    private CourseDto course;
    @Schema(description = "Дата и время дедлайна")
    private LocalDateTime deadline;
}
