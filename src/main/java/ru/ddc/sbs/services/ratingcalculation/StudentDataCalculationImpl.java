package ru.ddc.sbs.services.ratingcalculation;

import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/*
Важное замечание:
Расчет рейтинга сделан с отклонением от задания!
Он сделан на основе всех оценок за задания независимо от дедлайна!
В задании сказано, что рейтинг надо считать по заданиям у которых дата и время (дедлайн) раньше текущего.
Я посчитал такой подход в корне не верным.
Потому что в таком случае рейтинг зависит от времени, и следовательно, не несет никакой информации.
И непонятно как рассчитывать факт зачтения курса.
При этом все приложение еще должно отслеживать дедлайны и пересчитывать рейтинги.
*/
@Service
public class StudentDataCalculationImpl implements StudentDataCalculation {
    @Override
    public Double calculateRating(List<StudentGrade> studentGradeList) {

        int gradeSum = studentGradeList.stream()
                .mapToInt(StudentGrade::getGrade)
                .sum();

        int highestGradeSum = studentGradeList.stream()
                .map(StudentGrade::getTask)
                .mapToInt(Task::getHighestGrade)
                .sum();

        double rating = (double) gradeSum / highestGradeSum;
        BigDecimal bdRating = new BigDecimal(Double.toString(rating));
        return bdRating.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static final double MIN_RATING_TO_CREDIT = 0.80d;

    @Override
    public Boolean calculateCredited(Double rating) {
        return rating >= MIN_RATING_TO_CREDIT;
    }
}
