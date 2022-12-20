package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Schema(description = "DTO \"Задание\"")
public class TaskDto implements Serializable {
    @Schema(description = "Идентификатор задания")
    private Long id;
    @Schema(description = "Название задания")
    @NotBlank(message = "Не указано название задания")
    private String name;
    @Schema(description = "Максимальный балл")
    @Min(value = 1, message = "Максимальный балл за задание не может быть меньше 1")
    private Integer highestGrade;
}