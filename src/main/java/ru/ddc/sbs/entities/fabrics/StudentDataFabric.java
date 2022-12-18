package ru.ddc.sbs.entities.fabrics;

import org.springframework.stereotype.Component;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.entities.StudentData;
import ru.ddc.sbs.entities.StudentDataKey;

@Component
public class StudentDataFabric {
    public StudentData getEntity(Course course, Student student, Double rating, Boolean isCredited) {
        StudentDataKey studentDataKey = new StudentDataKey();
        studentDataKey.setStudentId(student.getId());
        studentDataKey.setCourseId(course.getId());
        StudentData studentData = new StudentData();
        studentData.setStudentDataKey(studentDataKey);
        studentData.setCourse(course);
        studentData.setStudent(student);
        studentData.setRating(rating);
        studentData.setCredited(isCredited);
        return studentData;
    }
}
