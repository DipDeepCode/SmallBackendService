package ru.ddc.sbs.services.taskdeadline;

import ru.ddc.sbs.entities.TaskDeadline;
import ru.ddc.sbs.exceptions.PersistException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskDeadlineService {
    void linkTaskToCourse(Long courseId, Long taskId, LocalDateTime deadline) throws PersistException;
    List<TaskDeadline> findTasksByCourseId(Long courseId);
    void unlinkTaskFromCourse(Long taskId, Long courseId);
    Boolean isTaskExistsInCourse(Long taskId, Long courseId);
    TaskDeadline findTask(Long courseId, Long taskId);
    boolean isNewStartDateIsBeforeEarliestDeadline(Long courseId, LocalDate newStartDate);
    boolean isNewEndDateIsAfterLatestDeadline(Long courseId, LocalDate newEndDate);
}
