package ru.ddc.sbs.services.student;

import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);
    List<Student> findAllStudents();
    List<Student> findAllStudentsByGroupName(String groupNumber);
    List<Student> findAllStudentsByFullName(FullName fullName);
    Student findStudentById(Long studentId);
    void updateStudentById(FullName newFullName, String newGroupNumber, Long studentId);
    void deactivateStudentById(Long studentId);
}
