package ru.ddc.sbs.services.course;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.exceptions.PersistError;
import ru.ddc.sbs.repositories.CourseRepository;
import ru.ddc.sbs.services.taskdeadline.TaskDeadlineService;

import javax.persistence.EntityNotFoundException;
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
    public Course addCourse(Course course) throws PersistError {
        try {
            return courseRepository.saveAndFlush(course);
        } catch (DataIntegrityViolationException e) {
            throw new PersistError("Курс с таким названием уже существует");
        }
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findALLByOrderByNameAsc();
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Курс c id = " + courseId + " не найден"));
    }

    @Override
    public Course findCourseByName(String courseName) {
        return courseRepository.findByName(courseName).orElseThrow(() -> new EntityNotFoundException("Курс с именем " + courseName + " не найден"));
    }

    @Override
    public void updateCourseById(String newName, LocalDate newStartDate, LocalDate newEndDate, Long courseId) throws ApiError {
        if (!taskDeadlineService.isNewStartDateIsBeforeEarliestDeadline(courseId, newStartDate)) {
            throw new ApiError("Начальная дата курса позже первого дедлайна");
        } else if (!taskDeadlineService.isNewEndDateIsAfterLatestDeadline(courseId, newEndDate)) {
            throw new ApiError("Конечная дата курса раньше последнего дедлайна");
        } else {
            courseRepository.updateCourseById(newName, newStartDate, newEndDate, courseId);
        }
    }

    @Override
    public void deactivateCourseById(Long courseId) {
        courseRepository.deactivateCourseById(false, courseId);
    }
}
