package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "DTO \"Курс\"")
public class CourseDto implements Serializable {
    @Schema(description = "Идентификатор курса")
    private Long id;
    @Schema(description = "Название курса")
    @NotBlank(message = "Не указано название курса")
    private String name;
    @Schema(description = "Дата начала курса")
    @NotNull(message = "Не указана дата начала курса")
    private LocalDate startDate;
    @Schema(description = "Дата окончания курса")
    @NotNull(message = "Не указана дата окончания курса")
    private LocalDate endDate;
}