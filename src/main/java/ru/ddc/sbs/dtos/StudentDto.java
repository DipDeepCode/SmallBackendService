package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "DTO \"Студент\"")
public class StudentDto {
    @Schema(description = "Идентификатор студента")
    private Long id;
    @Valid
    @NotNull(message = "Не указано полное имя студента")
    private FullNameDto fullNameDto;
    @Schema(description = "Номер группы")
    @NotBlank(message = "Не указан номер группы")
    private String groupNumber;
}
