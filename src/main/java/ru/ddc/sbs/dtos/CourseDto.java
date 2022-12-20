package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CourseDto implements Serializable {
    private Long id;
    @NotBlank(message = "Не указано название курса")
    private String name;
    @NotNull(message = "Не указана дата начала курса")
    private LocalDate startDate;
    @NotNull(message = "Не указана дата окончания курса")
    private LocalDate endDate;
}