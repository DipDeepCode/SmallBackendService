package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDeadlineDto {
    private TaskDto task;
    private CourseDto course;
    private LocalDateTime deadline;
}
