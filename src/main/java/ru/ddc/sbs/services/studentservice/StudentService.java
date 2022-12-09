package ru.ddc.sbs.services.studentservice;

import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;

import java.util.List;

public interface StudentService {

    Student addNewStudent(Student student);
    List<Student> getAllActiveStudents();
    List<Student> getAllActiveStudentsByCourse(Long courseId);
    List<Student> getAllActiveStudentsByGroup(String groupNumber);
    Student getActiveStudent(Long studentId);
    Student updateStudent(Long studentId,
                          String newFirstName,
                          String newLastName,
                          String newPatronymic,
                          String newGroupNumber);
    List<Student> getListOfStudentChanges(Long studentId);
    void removeStudent(Long studentId);
    List<Course> getCourses(Long studentId);
    void addCourse(Long studentId, Long courseId);
    void removeCourse(Long studentId, Long courseId);
}
