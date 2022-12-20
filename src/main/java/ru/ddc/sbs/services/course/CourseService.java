package ru.ddc.sbs.services.course;

import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.exceptions.PersistError;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {

    Course addCourse(Course course) throws PersistError;
    List<Course> findAllCourses();
    Course findCourseById(Long courseId);
    Course findCourseByName(String courseName);
    void updateCourseById(String newName, LocalDate newStartDate, LocalDate newEndDate, Long courseId) throws ApiError;
    void deactivateCourseById(Long courseId);
}
