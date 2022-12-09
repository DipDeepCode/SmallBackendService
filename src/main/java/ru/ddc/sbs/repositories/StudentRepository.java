package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.sbs.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByIsActiveTrue();
    List<Student> findAllByGroupNumberAndIsActiveTrue(String groupNumber);
    Optional<Student> findByIdAndIsActiveTrue(Long studentId);
}