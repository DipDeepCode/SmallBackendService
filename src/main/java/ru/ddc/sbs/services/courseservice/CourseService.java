package ru.ddc.sbs.services.courseservice;

import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.AddEntityException;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {

    Course addNewCourse(Course course) throws AddEntityException;
    List<Course> getAllActiveCourses();
    Course getActiveCourse(Long courseId);
    Course updateCourse(Long courseId, String newName, LocalDate newStartDate, LocalDate newEndDate);
    List<Course> getCourseHistory(Long courseId);
    void removeCourse(Long courseId);
}
