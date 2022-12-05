package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto implements Serializable {
    private Long id;
    private Long courseId;
    private String name;
    private LocalDateTime deadline;
    private Integer highestMark;
}