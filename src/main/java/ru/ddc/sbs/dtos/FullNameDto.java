package ru.ddc.sbs.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "DTO \"Полное имя\"")
public class FullNameDto {
    @Schema(description = "Имя")
    @NotBlank(message = "Не указано имя")
    private String firstName;
    @Schema(description = "Фамилия")
    @NotBlank(message = "Не указана фамилия")
    private String lastName;
    @Schema(description = "Отчество")
    private String patronymic;
}
