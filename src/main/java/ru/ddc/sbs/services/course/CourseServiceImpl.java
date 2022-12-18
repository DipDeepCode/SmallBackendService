package ru.ddc.sbs.services.course;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.PersistException;
import ru.ddc.sbs.repositories.CourseRepository;
import ru.ddc.sbs.services.taskdeadline.TaskDeadlineService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TaskDeadlineService taskDeadlineService;

    public CourseServiceImpl(CourseRepository courseRepository,
                             @Lazy TaskDeadlineService taskDeadlineService) {
        this.courseRepository = courseRepository;
        this.taskDeadlineService = taskDeadlineService;
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow();
    }

    @Override
    public Course findCourseByName(String courseName) {
        return courseRepository.findByName(courseName).orElseThrow();
    }

    @Override
    public void updateCourseById(String newName, LocalDate newStartDate, LocalDate newEndDate, Long courseId) throws PersistException {
        if (!taskDeadlineService.isNewStartDateIsBeforeEarliestDeadline(courseId, newStartDate)) {
            throw new PersistException("Начальная дата курса позже первого дедлайна");
        } else if (!taskDeadlineService.isNewEndDateIsAfterLatestDeadline(courseId, newEndDate)) {
            throw new PersistException("Конечная дата курса раньше последнего дедлайна");
        } else {
            courseRepository.updateCourseById(newName, newStartDate, newEndDate, courseId);
        }
    }

    @Override
    public void deactivateCourseById(Long courseId) {
        courseRepository.deactivateCourseById(false, courseId);
    }
}
