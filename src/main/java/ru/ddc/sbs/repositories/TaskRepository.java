package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.sbs.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByCourseIdAndIsActiveTrue(Long courseId);
    Optional<Task> findByCourseIdAndIdAndIsActiveTrue(Long courseId, Long taskId);
    Optional<Task> findByCourseIdAndNameAndIsActiveTrue(Long courseId, String name);
}