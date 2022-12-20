package ru.ddc.sbs.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.StudentDto;
import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.services.student.StudentService;

import javax.validation.Valid;
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
    public StudentDto addStudent(@Valid @RequestBody StudentDto studentDto) {
        Student requestBodyStudent = customMapper.map(studentDto, Student.class);
        Student addedStudent = studentService.addStudent(requestBodyStudent);
        return customMapper.map(addedStudent, StudentDto.class);
    }

    @GetMapping("/findAllStudents")
    public List<StudentDto> findAllStudents() {
        List<Student> allStudents = studentService.findAllStudents();
        return customMapper.mapList(allStudents, StudentDto.class);
    }

    @GetMapping("/findAllStudentsByGroupNumber")
    public List<StudentDto> findAllStudentsByGroupNumber(@RequestParam String groupName) {
        List<Student> allStudentsByGroupNumber = studentService.findAllStudentsByGroupNumber(groupName);
        return customMapper.mapList(allStudentsByGroupNumber, StudentDto.class);
    }

    @GetMapping("/findAllStudentsByFullName")
    public List<StudentDto> findAllStudentsByFullName(@Valid @RequestBody FullName fullName) {
        List<Student> allStudentsByFullName = studentService.findAllStudentsByFullName(fullName);
        return customMapper.mapList(allStudentsByFullName, StudentDto.class);
    }

    @GetMapping("/findStudentById")
    public StudentDto findStudentById(@RequestParam Long studentId) {
        Student studentById = studentService.findStudentById(studentId);
        return customMapper.map(studentById, StudentDto.class);
    }

    @PutMapping("/updateStudentById")
    public void updateStudentById(@Valid @RequestBody FullName newFullName,
                                  @RequestParam String newGroupNumber,
                                  @RequestParam Long studentId) {
        studentService.updateStudentById(newFullName, newGroupNumber, studentId);
    }

    @PutMapping("/deactivateStudentById")
    public void deactivateStudentById(@RequestParam Long studentId) {
        studentService.deactivateStudentById(studentId);
    }
}
