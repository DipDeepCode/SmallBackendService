package ru.ddc.sbs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.StudentGradeDto;
import ru.ddc.sbs.entities.StudentGrade;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.services.studentgrade.StudentGradeService;

import java.util.List;

@Tag(
        name = "StudentGradeController",
        description = "Обеспечивает объединение курса, задания и студента, установку и изменение оценки, " +
                "вызывает расчет рейтинга и статуса зачета курса"
)
@RestController
@RequestMapping("/studentGrade")
public class StudentGradeController {
    private final StudentGradeService studentGradeService;
    private final CustomMapper customMapper;

    public StudentGradeController(StudentGradeService studentGradeService,
                                  CustomMapper customMapper) {
        this.studentGradeService = studentGradeService;
        this.customMapper = customMapper;
    }

    @Operation(
            summary = "Добавление оценки по id курса, id студента и id задания",
            description = "Объединяет курс, задание и студента, устанавливает оценку за данное задание " +
                    "и инициирует расчет рейтинга и статуса зачета курса"
    )
    @PostMapping("/addGrade")
    public void addGrade(@RequestParam @Parameter(description = "id студента") Long studentId,
                         @RequestParam @Parameter(description = "id курса") Long courseId,
                         @RequestParam @Parameter(description = "id задания") Long taskId,
                         @RequestParam @Parameter(description = "Оценка") Integer grade) throws ApiError {
        studentGradeService.addGrade(studentId, courseId, taskId, grade);
    }

    @Operation(
            summary = "Получение оценок по id курса и id студента",
            description = "Возвращает список оценок за по всем заданиям у данного студента на данном курсе"
    )
    @GetMapping("/getGrades")
    public List<StudentGradeDto> getGrades(@RequestParam @Parameter(description = "id студента") Long studentId,
                                           @RequestParam @Parameter(description = "id курса") Long courseId) {
        List<StudentGrade> grades = studentGradeService.getGrades(studentId, courseId);
        return customMapper.mapList(grades, StudentGradeDto.class);
    }

    @Operation(
            summary = "Изменение оценки по id курса, id студента и id задания",
            description = "Изменяет оценку за данное задание и инициирует расчет рейтинга и статуса зачета курса"
    )
    @PutMapping("/updateGrade")
    public void updateGrade(@RequestParam @Parameter(description = "id студента") Long studentId,
                            @RequestParam @Parameter(description = "id курса") Long courseId,
                            @RequestParam @Parameter(description = "id задания") Long taskId,
                            @RequestParam @Parameter(description = "Новая оценка") Integer newGrade) throws ApiError {
        studentGradeService.addGrade(studentId, courseId, taskId, newGrade);
    }
}
