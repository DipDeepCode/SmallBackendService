package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StudentDto {
    private Long id;
    @Valid
    @NotNull(message = "Не указаны данные по ФИО")
    private FullNameDto fullNameDto;
    @NotBlank(message = "Не указан номер группы")
    private String groupNumber;
}
