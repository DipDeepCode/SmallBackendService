package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.sbs.entities.StudentData;
import ru.ddc.sbs.entities.StudentDataKey;

import java.util.List;
import java.util.Optional;

public interface StudentDataRepository extends JpaRepository<StudentData, StudentDataKey> {
    Optional<StudentData> findByStudentDataKey_StudentIdAndStudentDataKey_CourseId(Long studentId, Long courseId);
    boolean existsByStudentDataKey_StudentIdAndStudentDataKey_CourseId(Long studentId, Long courseId);
    List<StudentData> findByStudentDataKey_StudentId(Long studentId);
    List<StudentData> findByStudentDataKey_CourseId(Long courseId);
    void deleteByStudentDataKey_StudentIdAndStudentDataKey_CourseId(Long studentId, Long courseId);
}