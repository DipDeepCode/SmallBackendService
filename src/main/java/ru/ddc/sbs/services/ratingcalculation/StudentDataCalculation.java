package ru.ddc.sbs.services.ratingcalculation;

import ru.ddc.sbs.entities.StudentGrade;

import java.util.List;

public interface StudentDataCalculation {
    Double calculateRating(List<StudentGrade> studentGradeList);
    Boolean calculateCredited(Double rating);
}
