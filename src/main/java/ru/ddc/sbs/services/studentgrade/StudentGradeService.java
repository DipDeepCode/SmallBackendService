package ru.ddc.sbs.services.studentgrade;

import ru.ddc.sbs.entities.StudentGrade;
import ru.ddc.sbs.exceptions.ApiError;

import java.util.List;

public interface StudentGradeService {
    void addGrade(Long studentId, Long courseId, Long taskId, Integer grade) throws ApiError;
    List<StudentGrade> getGrades(Long studentId, Long courseId);
}
