package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByName();
    Optional<Task> findByName(String name);
    @Transactional
    @Modifying
    @Query("update Task t set t.name = ?1, t.highestGrade = ?2 where t.id = ?3")
    void updateTaskById(String name, Integer highestGrade, Long id);
}