package ru.ddc.sbs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.CourseDto;
import ru.ddc.sbs.dtos.StudentDto;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.services.studentservice.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    private final CustomMapper customMapper;

    @Autowired
    public StudentController(StudentService studentService,
                             CustomMapper customMapper) {
        this.studentService = studentService;
        this.customMapper = customMapper;
    }

    @PostMapping("/addNewStudent")
    public StudentDto addNewStudent(@RequestBody StudentDto studentDto) {
        Student student = customMapper.map(studentDto, Student.class);
        return customMapper.map(studentService.addNewStudent(student), StudentDto.class);
    }

    @GetMapping("/getAllActiveStudents")
    public List<StudentDto> getAllActiveStudents() {
        return customMapper.mapList(studentService.getAllActiveStudents(), StudentDto.class);
    }

    @GetMapping("/getAllActiveStudentsByCourse")
    public List<StudentDto> getAllActiveStudentsByCourse(@RequestParam Long courseId) {
        return customMapper.mapList(studentService.getAllActiveStudentsByCourse(courseId), StudentDto.class);
    }

    @GetMapping("/getAllActiveStudentsByGroup")
    public List<StudentDto> getAllActiveStudentsByGroup(@RequestParam String groupNumber) {
        return customMapper.mapList(studentService.getAllActiveStudentsByGroup(groupNumber), StudentDto.class);
    }

    @GetMapping("/getActiveStudent")
    public StudentDto getActiveStudent(@RequestParam Long studentId) {
        return customMapper.map(studentService.getActiveStudent(studentId), StudentDto.class);
    }

    @PutMapping("/updateStudent")
    public StudentDto updateStudent(@RequestParam Long studentId,
                                    @RequestParam String newFirstName,
                                    @RequestParam String newLastName,
                                    @RequestParam String newPatronymic,
                                    @RequestParam String newGroupNumber) {
        return customMapper.map(studentService.updateStudent(studentId,
                newFirstName,
                newLastName,
                newPatronymic,
                newGroupNumber), StudentDto.class);
    }

    @GetMapping("/getListOfStudentChanges")
    public List<StudentDto> getListOfStudentChanges(Long studentId) {
        return customMapper.mapList(studentService.getListOfStudentChanges(studentId), StudentDto.class);
    }

    @DeleteMapping("/removeStudent")
    public void removeStudent(@RequestParam Long studentId) {
        studentService.removeStudent(studentId);
    }

    @GetMapping("/getCourses")
    public List<CourseDto> getCourses(Long studentId) {
        return customMapper.mapList(studentService.getCourses(studentId), CourseDto.class);
    }

    @PostMapping("/addCourse")
    public void addCourse(@RequestParam Long studentId,
                          @RequestParam Long courseId) {
        studentService.addCourse(studentId, courseId);
    }

    @DeleteMapping("/removeCourse")
    public void removeCourse(@RequestParam Long studentId,
                             @RequestParam Long courseId) {
        studentService.removeCourse(studentId, courseId);
    }
}
