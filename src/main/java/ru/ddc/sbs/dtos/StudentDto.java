package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String groupNumber;
}
