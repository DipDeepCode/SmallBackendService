package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class TaskDto implements Serializable {
    private Long id;
    @NotBlank(message = "Не указано название задания")
    private String name;
    @Min(value = 1, message = "Максимальный балл за задание не может быть меньше 1")
    private Integer highestGrade;
}