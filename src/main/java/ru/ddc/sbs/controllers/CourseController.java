package ru.ddc.sbs.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.CourseDto;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.exceptions.PersistError;
import ru.ddc.sbs.services.course.CourseService;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("/addCourse")
    public CourseDto addCourse(@RequestBody CourseDto courseDto) throws PersistError {
        Course course = customMapper.map(courseDto, Course.class);
        return customMapper.map(courseService.addCourse(course), CourseDto.class);
    }

    @GetMapping("/findAllCourses")
    public List<CourseDto> findAllCourses() {
        return customMapper.mapList(courseService.findAllCourses(), CourseDto.class);
    }

    @GetMapping("/findCourseById")
    public CourseDto findCourseById(@RequestParam Long courseId) {
        return customMapper.map(courseService.findCourseById(courseId), CourseDto.class);
    }

    @GetMapping("/findCourseByName")
    public CourseDto findCourseByName(@RequestParam String courseName) {
        return customMapper.map(courseService.findCourseByName(courseName), CourseDto.class);
    }

    @PutMapping("/updateCourseById")
    public void updateCourseById(@RequestParam String newName,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newStartDate,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newEndDate,
                                 @RequestParam Long courseId) throws ApiError {
        courseService.updateCourseById(newName, newStartDate, newEndDate, courseId);
    }

    @PutMapping("/deactivateCourseById")
    public void deactivateCourseById(@RequestParam Long courseId) {
        courseService.deactivateCourseById(courseId);
    }
}
