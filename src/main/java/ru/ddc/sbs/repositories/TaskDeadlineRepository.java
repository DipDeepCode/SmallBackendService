package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.sbs.entities.TaskDeadline;
import ru.ddc.sbs.entities.TaskDeadlineKey;

import java.util.List;
import java.util.Optional;

public interface TaskDeadlineRepository extends JpaRepository<TaskDeadline, TaskDeadlineKey> {
    Optional<TaskDeadline> findByTaskDeadlineKey_CourseIdAndTaskDeadlineKey_TaskId(Long courseId, Long taskId);
    boolean existsByTaskDeadlineKey_TaskIdAndTaskDeadlineKey_CourseId(Long taskId, Long courseId);
    List<TaskDeadline> findByTaskDeadlineKey_CourseIdOrderByDeadlineAsc(Long courseId);
    void deleteByTaskDeadlineKey_TaskIdAndTaskDeadlineKey_CourseId(Long taskId, Long courseId);
    Optional<TaskDeadline> findFirstByTaskDeadlineKey_CourseIdOrderByDeadlineAsc(Long courseId);
    Optional<TaskDeadline> findFirstByTaskDeadlineKey_CourseIdOrderByDeadlineDesc(Long courseId);
}