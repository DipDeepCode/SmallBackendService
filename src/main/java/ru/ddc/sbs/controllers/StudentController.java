package ru.ddc.sbs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.FullNameDto;
import ru.ddc.sbs.dtos.StudentDto;
import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.services.student.StudentService;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name = "StudentController",
        description = "Обеспечивает работу со студентами"
)
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

    @Operation(
            summary = "Добавление студента в базу данных",
            description = "Добавляет студента (полное имя, номер группы) в базу данных. Признак активности устанавливается в true"
    )
    @PostMapping("/addStudent")
    public StudentDto addStudent(@Valid @RequestBody StudentDto studentDto) {
        Student requestBodyStudent = customMapper.map(studentDto, Student.class);
        Student addedStudent = studentService.addStudent(requestBodyStudent);
        return customMapper.map(addedStudent, StudentDto.class);
    }

    @Operation(
            summary = "Получение всех студентов",
            description = "Возвращает список всех студентов отсортированный по полному имени"
    )
    @GetMapping("/findAllStudents")
    public List<StudentDto> findAllStudents() {
        List<Student> allStudents = studentService.findAllStudents();
        return customMapper.mapList(allStudents, StudentDto.class);
    }

    @Operation(
            summary = "Поиск студентов по номеру группы",
            description = "Возвращает список студентов найденных по номеру группы"
    )
    @GetMapping("/findAllStudentsByGroupNumber")
    public List<StudentDto> findAllStudentsByGroupNumber(@RequestParam @Parameter(description = "Номер группы") String groupName) {
        List<Student> allStudentsByGroupNumber = studentService.findAllStudentsByGroupNumber(groupName);
        return customMapper.mapList(allStudentsByGroupNumber, StudentDto.class);
    }

    @Operation(
            summary = "Поиск студентов по полному имени",
            description = "Возвращает список студентов найденных по полному имени"
    )
    @GetMapping("/findAllStudentsByFullName")
    public List<StudentDto> findAllStudentsByFullName(@Valid @RequestBody FullNameDto fullNameDto) {
        FullName fullName = customMapper.map(fullNameDto, FullName.class);
        List<Student> allStudentsByFullName = studentService.findAllStudentsByFullName(fullName);
        return customMapper.mapList(allStudentsByFullName, StudentDto.class);
    }

    @Operation(
            summary = "Поиск студента по его идентификатору",
            description = "Возвращает студента найденного по его идентификатору"
    )
    @GetMapping("/findStudentById")
    public StudentDto findStudentById(@RequestParam @Parameter(description = "id студента") Long studentId) {
        Student studentById = studentService.findStudentById(studentId);
        return customMapper.map(studentById, StudentDto.class);
    }

    @Operation(
            summary = "Изменение данных студента",
            description = "Позволяет изменить полное имя и номер группы студента"
    )
    @PutMapping("/updateStudentById")
    public void updateStudentById(@Valid @RequestBody FullNameDto newFullNameDto,
                                  @RequestParam @Parameter(description = "Новый номер группы") String newGroupNumber,
                                  @RequestParam @Parameter(description = "id изменяемого студента") Long studentId) {
        FullName newFullName = customMapper.map(newFullNameDto, FullName.class);
        studentService.updateStudentById(newFullName, newGroupNumber, studentId);
    }

    @Operation(
            summary = "Деактивация студента",
            description = "Устанавливает у студента признак активности в false"
    )
    @PutMapping("/deactivateStudentById")
    public void deactivateStudentById(@RequestParam @Parameter(description = "id деактивируемого студента") Long studentId) {
        studentService.deactivateStudentById(studentId);
    }
}
