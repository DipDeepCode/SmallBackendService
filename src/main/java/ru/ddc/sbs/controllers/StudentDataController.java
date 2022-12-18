package ru.ddc.sbs.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.CourseDto;
import ru.ddc.sbs.dtos.StudentDataDto;
import ru.ddc.sbs.dtos.StudentDto;
import ru.ddc.sbs.services.studentdata.StudentDataService;

import java.util.List;

@RestController
@RequestMapping("/studentData")
public class StudentDataController {
    private final StudentDataService studentDataService;
    private final CustomMapper customMapper;

    public StudentDataController(StudentDataService studentDataService,
                                 CustomMapper customMapper) {
        this.studentDataService = studentDataService;
        this.customMapper = customMapper;
    }

    @PostMapping("/linkCourseToStudent")
    public void linkCourseToStudent(@RequestParam Long courseId,
                                    @RequestParam Long studentId) {
        studentDataService.linkCourseToStudent(courseId, studentId);
    }

    @GetMapping("/findStudentData")
    public StudentDataDto findStudentData(@RequestParam Long courseId,
                                          @RequestParam Long studentId) {
        return customMapper.map(studentDataService.findStudentData(courseId, studentId), StudentDataDto.class);
    }

    @GetMapping("/findStudentDataByStudentId")
    public List<StudentDataDto> findStudentDataByStudentId(@RequestParam Long studentId) {
        return customMapper.mapList(studentDataService.findStudentDataByStudentId(studentId), StudentDataDto.class);
    }

    @GetMapping("/findStudentsByCourseId")
    public List<StudentDto> findStudentsByCourseId(@RequestParam Long courseId) {
        return customMapper.mapList(studentDataService.findStudentsByCourseId(courseId), StudentDto.class);
    }

    @GetMapping("/findCoursesByStudentId")
    public List<CourseDto> findCoursesByStudentId(@RequestParam Long studentId) {
        return customMapper.mapList(studentDataService.findCoursesByStudentId(studentId), CourseDto.class);
    }

    @DeleteMapping("/unlinkCourseFromStudent")
    public void unlinkCourseFromStudent(@RequestParam Long courseId,
                                        @RequestParam Long studentId) {
        studentDataService.unlinkCourseFromStudent(studentId, courseId);
    }
}
