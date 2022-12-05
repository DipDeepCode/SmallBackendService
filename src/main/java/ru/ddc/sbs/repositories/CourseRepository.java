package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.sbs.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByIdAndIsActiveTrue(Long id);
    List<Course> findAllByIsActiveTrue();
    Optional<Course> findByNameAndIsActiveTrue(String courseName);
}