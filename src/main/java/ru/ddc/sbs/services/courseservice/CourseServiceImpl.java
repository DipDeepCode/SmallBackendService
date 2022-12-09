package ru.ddc.sbs.services.courseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.exceptions.AddEntityException;
import ru.ddc.sbs.repositories.CourseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addNewCourse(Course course) throws AddEntityException {
        //TODO добавить валидацию
        String name = course.getName();
        if (courseRepository.findByNameAndIsActiveTrue(name).isEmpty()) {
            return courseRepository.save(course);
        } else {
            throw new AddEntityException("Курс с таким названием уже существует");
        }
    }

    @Override
    public List<Course> getAllActiveCourses() {
        return courseRepository.findAllByIsActiveTrue();
    }

    @Override
    public Course getActiveCourse(Long courseId) {
        return courseRepository.findByIdAndIsActiveTrue(courseId).orElseThrow();
    }

    @Transactional
    @Override
    public Course updateCourse(Long courseId, String newName, LocalDate newStartDate, LocalDate newEndDate) {
        LocalDateTime updateDateTime = LocalDateTime.now();
        Course originalCourse = getActiveCourse(courseId);
        Course clonedCourse = originalCourse.clone();

        originalCourse.setIsActive(false);
        originalCourse.setEntryEndDate(updateDateTime);
        originalCourse.setTasks(null);
//        originalCourse.setStudents(null);
        courseRepository.save(originalCourse);

        clonedCourse.setId(null);
        clonedCourse.setName(newName);
        clonedCourse.setStartDate(newStartDate);
        clonedCourse.setEndDate(newEndDate);
        clonedCourse.getTasks().forEach(task -> task.setCourse(clonedCourse));
//        clonedCourse.getStudents().forEach(student -> {
//            student.getCourses().remove(originalCourse);
//            student.getCourses().add(clonedCourse);
//        });
        clonedCourse.setEntryStartDate(updateDateTime);
        clonedCourse.setLinkToPreviousEntry(originalCourse);
        return courseRepository.save(clonedCourse);
    }

    @Override
    public List<Course> getListOfCourseChanges(Long courseId) {
        List<Course> listOfCourseChanges = new ArrayList<>();
        Course course = getActiveCourse(courseId);
        do {
            listOfCourseChanges.add(course);
            course = course.getLinkToPreviousEntry();
        } while (course != null);
        return listOfCourseChanges;
    }

    @Transactional
    @Override
    public void removeCourse(Long courseId) {
        Course course = getActiveCourse(courseId);
        course.setIsActive(false);
        course.setEntryEndDate(LocalDateTime.now());
        courseRepository.save(course);
    }

    @Override
    public List<Student> getStudents(Long courseId) {
        return null;//getActiveCourse(courseId).getStudents();
    }
}
