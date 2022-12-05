package ru.ddc.sbs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.CourseDto;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.AddEntityException;
import ru.ddc.sbs.services.courseservice.CourseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final CustomMapper customMapper;

    @Autowired
    public CourseController(CourseService courseService,
                            CustomMapper customMapper) {
        this.courseService = courseService;
        this.customMapper = customMapper;
    }

    @PostMapping("/addNewCourse")
    public CourseDto addNewCourse(@RequestBody CourseDto courseDto) throws AddEntityException {
        Course course = customMapper.map(courseDto, Course.class);
        return customMapper.map(courseService.addNewCourse(course), CourseDto.class);
    }

    @GetMapping("/getAllActiveCourses")
    public List<CourseDto> getAllActiveCourses() {
        return customMapper.mapList(courseService.getAllActiveCourses(), CourseDto.class);
    }

    @GetMapping("/getActiveCourse")
    public CourseDto getActiveCourse(@RequestParam Long courseId) {
        return customMapper.map(courseService.getActiveCourse(courseId), CourseDto.class);
    }

    @PutMapping("/updateCourse")
    public CourseDto updateCourse(@RequestParam Long courseId,
                                  @RequestParam String newName,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newStartDate,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newEndDate) {
        return customMapper.map(courseService.updateCourse(courseId, newName, newStartDate, newEndDate), CourseDto.class);
    }

    @GetMapping("/getCourseHistory")
    public List<CourseDto> getCourseHistory(@RequestParam Long courseId) {
        return customMapper.mapList(courseService.getCourseHistory(courseId), CourseDto.class);
    }

    @DeleteMapping("/removeCourse")
    public void removeCourse(@RequestParam Long courseId) {
        courseService.removeCourse(courseId);
    }
}
