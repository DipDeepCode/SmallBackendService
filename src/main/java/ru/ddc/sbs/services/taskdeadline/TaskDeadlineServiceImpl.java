package ru.ddc.sbs.services.taskdeadline;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.entities.TaskDeadline;
import ru.ddc.sbs.entities.fabrics.TaskDeadlineFabric;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.repositories.TaskDeadlineRepository;
import ru.ddc.sbs.services.course.CourseService;
import ru.ddc.sbs.services.task.TaskService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskDeadlineServiceImpl implements TaskDeadlineService {
    private final CourseService courseService;
    private final TaskService taskService;
    private final TaskDeadlineFabric taskDeadlineFabric;
    private final TaskDeadlineRepository taskDeadlineRepository;

    public TaskDeadlineServiceImpl(CourseService courseService,
                                   TaskService taskService,
                                   TaskDeadlineFabric taskDeadlineFabric,
                                   TaskDeadlineRepository taskDeadlineRepository) {
        this.courseService = courseService;
        this.taskService = taskService;
        this.taskDeadlineFabric = taskDeadlineFabric;
        this.taskDeadlineRepository = taskDeadlineRepository;
    }

    @Override
    public void linkTaskToCourse(Long courseId, Long taskId, LocalDateTime deadline) throws ApiError {
        Course course = courseService.findCourseById(courseId);
        Task task = taskService.findTaskById(taskId);
        TaskDeadline taskDeadline = taskDeadlineFabric.getEntity(course, task, deadline);
        if (isDeadlineWithinTheCourse(course, deadline)) {
            taskDeadlineRepository.save(taskDeadline);
        } else {
            throw new ApiError("Дедлайн выходит за пределы курса");
        }
    }

    @Override
    public TaskDeadline findTask(Long courseId, Long taskId) {
        return taskDeadlineRepository.findByTaskDeadlineKey_CourseIdAndTaskDeadlineKey_TaskId(courseId, taskId)
                .orElseThrow(() -> new EntityNotFoundException("Задания с id = " + taskId + " нет в курсе с id = " + courseId));
    }

    @Override
    public List<TaskDeadline> findTasksByCourseId(Long courseId) {
        return taskDeadlineRepository.findByTaskDeadlineKey_CourseIdOrderByDeadlineAsc(courseId);
    }

    @Transactional
    @Override
    public void unlinkTaskFromCourse(Long taskId, Long courseId) {
        taskDeadlineRepository.deleteByTaskDeadlineKey_TaskIdAndTaskDeadlineKey_CourseId(taskId, courseId);
    }

    @Override
    public Boolean isTaskExistsInCourse(Long taskId, Long courseId) {
        return taskDeadlineRepository.existsByTaskDeadlineKey_TaskIdAndTaskDeadlineKey_CourseId(taskId, courseId);
    }

    private boolean isDeadlineWithinTheCourse(Course course, LocalDateTime deadline) {
        return deadline.isAfter(course.getStartDate().atStartOfDay()) &&
                deadline.isBefore(course.getEndDate().atStartOfDay());
    }

    @Override
    public boolean isNewStartDateIsBeforeEarliestDeadline(Long courseId, LocalDate newStartDate) {
        LocalDateTime earliestDeadline = taskDeadlineRepository.findFirstByTaskDeadlineKey_CourseIdOrderByDeadlineAsc(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Дедлайн не найден"))
                .getDeadline();
        return newStartDate.isBefore(earliestDeadline.toLocalDate());
    }

    @Override
    public boolean isNewEndDateIsAfterLatestDeadline(Long courseId, LocalDate newEndDate) {
        LocalDateTime latestDeadline = taskDeadlineRepository.findFirstByTaskDeadlineKey_CourseIdOrderByDeadlineDesc(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Дедлайн не найден"))
                .getDeadline();
        return newEndDate.isAfter(latestDeadline.toLocalDate());
    }
}
