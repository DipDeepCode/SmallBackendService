package ru.ddc.sbs.services.student;

import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.repositories.StudentRepository;

import javax.persistence.EntityNotFoundException;
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
        return studentRepository.findByOrderByFullName_FirstNameAscFullName_LastNameAscFullName_PatronymicAsc();
    }

    @Override
    public List<Student> findAllStudentsByGroupNumber(String groupNumber) {
        return studentRepository.findByGroupNumberOrderByFullName_FirstNameAscFullName_LastNameAscFullName_PatronymicAsc(groupNumber);
    }

    @Override
    public List<Student> findAllStudentsByFullName(FullName fullName) {
        return studentRepository.findByFullNameOrderByGroupNumber(fullName);
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Студент с id = " + studentId + " не найден"));
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
