package ru.ddc.sbs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.CourseDto;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.exceptions.PersistError;
import ru.ddc.sbs.services.course.CourseService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "CourseController",
        description = "Обеспечивает работу с курсами"
)
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final CustomMapper customMapper;

    public CourseController(CourseService courseService,
                            CustomMapper customMapper) {
        this.courseService = courseService;
        this.customMapper = customMapper;
    }

    @Operation(
            summary = "Добавление курса в базу данных",
            description = "Добавляет курс (название, дата начала и дата окончания курса). Признак активности устанавливается в true"
    )
    @PostMapping("/addCourse")
    public CourseDto addCourse(@Valid @RequestBody CourseDto courseDto) throws PersistError {
        Course requestBodyCourse = customMapper.map(courseDto, Course.class);
        Course addedCourse = courseService.addCourse(requestBodyCourse);
        return customMapper.map(addedCourse, CourseDto.class);
    }

    @Operation(
            summary = "Получение всех сохраненных курсов",
            description = "Возвращает список всех курсов отсортированный по названию"
    )
    @GetMapping("/findAllCourses")
    public List<CourseDto> findAllCourses() {
        List<Course> allCourses = courseService.findAllCourses();
        return customMapper.mapList(allCourses, CourseDto.class);
    }

    @Operation(
            summary = "Поиск курса по его идентификатору",
            description = "Возвращает курс найденный по его идентификатору"
    )
    @GetMapping("/findCourseById")
    public CourseDto findCourseById(@RequestParam @Parameter(description = "id курса") Long courseId) {
        Course courseById = courseService.findCourseById(courseId);
        return customMapper.map(courseById, CourseDto.class);
    }

    @Operation(
            summary = "Поиск курса по его названию",
            description = "Возвращает курс найденный по его названию"
    )
    @GetMapping("/findCourseByName")
    public CourseDto findCourseByName(@RequestParam @Parameter(description = "Название курса") String courseName) {
        Course courseByName = courseService.findCourseByName(courseName);
        return customMapper.map(courseByName, CourseDto.class);
    }

    @Operation(
            summary = "Изменение данных курса",
            description = "Позволяет изменить сразу название, дату начала и дату окончания курса"
    )
    @PutMapping("/updateCourseById")
    public void updateCourseById(@RequestParam @Parameter(description = "Новое название курса") String newName,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                 @Parameter(description = "Новая дата начала курса") LocalDate newStartDate,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                 @Parameter(description = "Новая дата окончания курса") LocalDate newEndDate,
                                 @RequestParam @Parameter(description = "id изменяемого курса") Long courseId) throws ApiError {
        courseService.updateCourseById(newName, newStartDate, newEndDate, courseId);
    }

    @Operation(
            summary = "Деактивация курса",
            description = "Устанавливает у курса признак активности в false"
    )
    @PutMapping("/deactivateCourseById")
    public void deactivateCourseById(@RequestParam @Parameter(description = "id деактивируемого курса") Long courseId) {
        courseService.deactivateCourseById(courseId);
    }
}
