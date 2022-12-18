package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TaskDto implements Serializable {
    private Long id;
    private String name;
    private Integer highestGrade;
}