package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.sbs.entities.*;

import java.util.List;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, StudentGradeKey> {
    List<StudentGrade> findByGradeKey_StudentIdAndGradeKey_CourseId(Long studentId, Long courseId);
}