package ru.ddc.sbs.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.StudentGradeDto;
import ru.ddc.sbs.entities.StudentGrade;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.services.studentgrade.StudentGradeService;

import java.util.List;

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

    @PostMapping("/addGrade")
    public void addGrade(@RequestParam Long studentId,
                         @RequestParam Long courseId,
                         @RequestParam Long taskId,
                         @RequestParam Integer grade) throws ApiError {
        studentGradeService.addGrade(studentId, courseId, taskId, grade);
    }

    @GetMapping("/getGrades")
    public List<StudentGradeDto> getGrades(@RequestParam Long studentId,
                                           @RequestParam Long courseId) {
        List<StudentGrade> grades = studentGradeService.getGrades(studentId, courseId);
        return customMapper.mapList(grades, StudentGradeDto.class);
    }

    @PutMapping("/updateGrade")
    public void updateGrade(@RequestParam Long studentId,
                            @RequestParam Long courseId,
                            @RequestParam Long taskId,
                            @RequestParam Integer newGrade) throws ApiError {
        studentGradeService.addGrade(studentId, courseId, taskId, newGrade);
    }
}
