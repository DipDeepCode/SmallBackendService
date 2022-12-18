package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Course;

import java.time.LocalDate;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    @Transactional
    @Modifying
    @Query("update Course c set c.name = ?1, c.startDate = ?2, c.endDate = ?3 where c.id = ?4")
    void updateCourseById(String name, LocalDate startDate, LocalDate endDate, Long id);
    @Transactional
    @Modifying
    @Query("update Course c set c.isActive = ?1 where c.id = ?2")
    void deactivateCourseById(Boolean isActive, Long id);
}