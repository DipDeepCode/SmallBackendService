package ru.ddc.sbs.services.student;

import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentsByGroupName(String groupNumber) {
        return studentRepository.findByGroupNumber(groupNumber);
    }

    @Override
    public List<Student> findAllStudentsByFullName(FullName fullName) {
        return studentRepository.findByFullName(fullName);
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }

    @Override
    public void updateStudentById(FullName newFullName, String newGroupNumber, Long studentId) {
        studentRepository.updateStudentById(newFullName, newGroupNumber, studentId);
    }

    @Override
    public void deactivateStudentById(Long studentId) {
        studentRepository.deactivateStudentById(false, studentId);
    }
}