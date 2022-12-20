package ru.ddc.sbs.services.studentdata;

import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.entities.StudentData;
import ru.ddc.sbs.entities.StudentGrade;

import java.util.List;

public interface StudentDataService {
    void linkCourseToStudent(Long courseId, Long studentId);
    StudentData findStudentData(Long courseId, Long studentId);
    List<StudentData> findStudentDataByStudentId(Long studentId);
    List<Student> findStudentsByCourseId(Long courseId);
    List<Course> findCoursesByStudentId(Long studentId);
    void unlinkCourseFromStudent(Long studentId, Long courseId);
    Boolean isStudentExistInCourse(Long studentId, Long courseId);
    void calculateStudentData(Long studentId, Long courseId, List<StudentGrade> studentGradeList);
}
