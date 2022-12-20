package ru.ddc.sbs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.CourseDto;
import ru.ddc.sbs.dtos.StudentDataDto;
import ru.ddc.sbs.dtos.StudentDto;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.entities.StudentData;
import ru.ddc.sbs.services.studentdata.StudentDataService;

import java.util.List;

@Tag(
        name = "StudentDataController",
        description = "Обеспечивает добавления студента на курс, удаление студента из курса, поиск данных по студенту (рейтинг, статус зачета курса)"
)
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

    @Operation(
            summary = "Добавление студента на курс по id студента и id курса",
            description = "Объединяет студента и курс, устанавливает рейтинг равный 0, статус зачета курса в false"
    )
    @PostMapping("/linkCourseToStudent")
    public void linkCourseToStudent(@RequestParam @Parameter(description = "id курса") Long courseId,
                                    @RequestParam @Parameter(description = "id студента") Long studentId) {
        studentDataService.linkCourseToStudent(courseId, studentId);
    }

    @Operation(
            summary = "Поиск данных по id студента и id курса",
            description = "Возвращает курс, студента, его рейтинг и статус зачета курса"
    )
    @GetMapping("/findStudentData")
    public StudentDataDto findStudentData(@RequestParam @Parameter(description = "id курса") Long courseId,
                                          @RequestParam @Parameter(description = "id студента") Long studentId) {
        StudentData studentData = studentDataService.findStudentData(courseId, studentId);
        return customMapper.map(studentData, StudentDataDto.class);
    }

    @Operation(
            summary = "Поиск данных по id студента",
            description = "Возвращает список курсов у данного студента, рейтинги и статусы зачета курса"
    )
    @GetMapping("/findStudentDataByStudentId")
    public List<StudentDataDto> findStudentDataByStudentId(@RequestParam @Parameter(description = "id студента") Long studentId) {
        List<StudentData> studentDataByStudentId = studentDataService.findStudentDataByStudentId(studentId);
        return customMapper.mapList(studentDataByStudentId, StudentDataDto.class);
    }

    @Operation(
            summary = "Поиск студентов по id курса",
            description = "Возвращает список студентов, добавленных на данный курс"
    )
    @GetMapping("/findStudentsByCourseId")
    public List<StudentDto> findStudentsByCourseId(@RequestParam @Parameter(description = "id курса") Long courseId) {
        List<Student> studentsByCourseId = studentDataService.findStudentsByCourseId(courseId);
        return customMapper.mapList(studentsByCourseId, StudentDto.class);
    }

    @Operation(
            summary = "Поиск курсов по id студента",
            description = "Возвращает список курсов на которые добавлен данный студент"
    )
    @GetMapping("/findCoursesByStudentId")
    public List<CourseDto> findCoursesByStudentId(@RequestParam @Parameter(description = "id студента") Long studentId) {
        List<Course> coursesByStudentId = studentDataService.findCoursesByStudentId(studentId);
        return customMapper.mapList(coursesByStudentId, CourseDto.class);
    }

    @Operation(
            summary = "Исключение студента из курса по id студента и id курса",
            description = "Исключает студента из курса (рейтинг и статус зачета курса при этом теряются)"
    )
    @DeleteMapping("/unlinkCourseFromStudent")
    public void unlinkCourseFromStudent(@RequestParam @Parameter(description = "id курса") Long courseId,
                                        @RequestParam @Parameter(description = "id студента") Long studentId) {
        studentDataService.unlinkCourseFromStudent(studentId, courseId);
    }
}
