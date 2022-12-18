package ru.ddc.sbs.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private FullNameDto fullNameDto;
    private String groupNumber;
}
