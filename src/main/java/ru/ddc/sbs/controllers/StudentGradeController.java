package ru.ddc.sbs.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.StudentGradeDto;
import ru.ddc.sbs.exceptions.PersistException;
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
                         @RequestParam Integer grade) throws PersistException {
        studentGradeService.addGrade(studentId, courseId, taskId, grade);
    }

    @GetMapping("/getGrades")
    public List<StudentGradeDto> getGrades(@RequestParam Long studentId,
                                           @RequestParam Long courseId) {
        return customMapper.mapList(studentGradeService.getGrades(studentId, courseId), StudentGradeDto.class);
    }

    @PutMapping("/updateGrade")
    public void updateGrade(@RequestParam Long studentId,
                            @RequestParam Long courseId,
                            @RequestParam Long taskId,
                            @RequestParam Integer newGrade) throws PersistException {
        studentGradeService.addGrade(studentId, courseId, taskId, newGrade);
    }
}
