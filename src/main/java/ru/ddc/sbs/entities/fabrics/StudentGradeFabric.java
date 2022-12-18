package ru.ddc.sbs.entities.fabrics;

import org.springframework.stereotype.Component;
import ru.ddc.sbs.entities.*;

@Component
public class StudentGradeFabric {
    public StudentGrade getEntity(Student student, Course course, Task task, Integer grade) {
        StudentGradeKey studentGradeKey = new StudentGradeKey();
        studentGradeKey.setStudentId(student.getId());
        studentGradeKey.setCourseId(course.getId());
        studentGradeKey.setTaskId(task.getId());
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setGradeKey(studentGradeKey);
        studentGrade.setStudent(student);
        studentGrade.setCourse(course);
        studentGrade.setTask(task);
        studentGrade.setGrade(grade);
        return studentGrade;
    }
}
