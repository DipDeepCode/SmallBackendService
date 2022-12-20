package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FullNameDto {
    @NotBlank(message = "Не указано имя")
    private String firstName;
    @NotBlank(message = "Не указана фамилия")
    private String lastName;
    private String patronymic;
}
