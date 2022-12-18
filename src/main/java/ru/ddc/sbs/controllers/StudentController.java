package ru.ddc.sbs.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.StudentDto;
import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.services.student.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final CustomMapper customMapper;

    public StudentController(StudentService studentService,
                             CustomMapper customMapper) {
        this.studentService = studentService;
        this.customMapper = customMapper;
    }

    @PostMapping("/addStudent")
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        Student student = customMapper.map(studentDto, Student.class);
        return customMapper.map(studentService.addStudent(student), StudentDto.class);
    }

    @GetMapping("/findAllStudents")
    public List<StudentDto> findAllStudents() {
        return customMapper.mapList(studentService.findAllStudents(), StudentDto.class);
    }

    @GetMapping("/findAllStudentsByGroupName")
    public List<StudentDto> findAllStudentsByGroupName(@RequestParam String groupName) {
        return customMapper.mapList(studentService.findAllStudentsByGroupName(groupName), StudentDto.class);
    }

    @GetMapping("/findAllStudentsByFullName")
    public List<StudentDto> findAllStudentsByFullName(@RequestBody FullName fullName) {
        return customMapper.mapList(studentService.findAllStudentsByFullName(fullName), StudentDto.class);
    }

    @GetMapping("/findStudentById")
    public StudentDto findStudentById(@RequestParam Long studentId) {
        return customMapper.map(studentService.findStudentById(studentId), StudentDto.class);
    }

    @PutMapping("/updateStudentById")
    public void updateStudentById(@RequestBody FullName newFullName,
                                  @RequestParam String newGroupNumber,
                                  @RequestParam Long studentId) {
        studentService.updateStudentById(newFullName, newGroupNumber, studentId);
    }

    @PutMapping("/deactivateStudentById")
    public void deactivateStudentById(@RequestParam Long studentId) {
        studentService.deactivateStudentById(studentId);
    }
}
