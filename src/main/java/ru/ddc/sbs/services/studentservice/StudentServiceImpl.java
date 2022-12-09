package ru.ddc.sbs.services.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.repositories.StudentRepository;
import ru.ddc.sbs.services.courseservice.CourseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              @Lazy CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllActiveStudents() {
        return studentRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Student> getAllActiveStudentsByCourse(Long courseId) {
        return courseService.getStudents(courseId);
    }

    @Override
    public List<Student> getAllActiveStudentsByGroup(String groupNumber) {
        return studentRepository.findAllByGroupNumberAndIsActiveTrue(groupNumber);
    }

    @Override
    public Student getActiveStudent(Long studentId) {
        return studentRepository.findByIdAndIsActiveTrue(studentId).orElseThrow();
    }

    @Override
    public Student updateStudent(Long studentId,
                                 String newFirstName,
                                 String newLastName,
                                 String newPatronymic,
                                 String newGroupNumber) {
        LocalDateTime updateDateTime = LocalDateTime.now();
        Student originalStudent  = getActiveStudent(studentId);
        Student clonedStudent = originalStudent.clone();

        originalStudent.setIsActive(false);
        originalStudent.setEntryEndDate(updateDateTime);
//        originalStudent.setCourses(null);
        studentRepository.save(originalStudent);

        clonedStudent.setId(null);
        clonedStudent.setFirstName(newFirstName);
        clonedStudent.setLastName(newLastName);
        clonedStudent.setPatronymic(newPatronymic);
        clonedStudent.setGroupNumber(newGroupNumber);
//        clonedStudent.getCourses().forEach(course -> {
//            course.getStudents().remove(originalStudent);
//            course.getStudents().add(clonedStudent);
//        });
        clonedStudent.setEntryStartDate(updateDateTime);
        clonedStudent.setLinkToPreviousEntry(originalStudent);
        return studentRepository.save(clonedStudent);
    }

    @Override
    public List<Student> getListOfStudentChanges(Long studentId) {
        List<Student> listOfStudentChanges = new ArrayList<>();
        Student student = getActiveStudent(studentId);
        do {
            listOfStudentChanges.add(student);
            student = student.getLinkToPreviousEntry();
        } while (student != null);
        return listOfStudentChanges;
    }

    @Transactional
    @Override
    public void removeStudent(Long studentId) {
        Student student = getActiveStudent(studentId);
        student.setIsActive(false);
        student.setEntryEndDate(LocalDateTime.now());
        studentRepository.save(student);
    }

    @Override
    public List<Course> getCourses(Long studentId) {
        return null;//getActiveStudent(studentId).getCourses();
    }

    @Override
    public void addCourse(Long studentId, Long courseId) {
        Student student = getActiveStudent(studentId);
        Course course = courseService.getActiveCourse(courseId);
//        student.getCourses().add(course);
        studentRepository.save(student);
    }

    @Override
    public void removeCourse(Long studentId, Long courseId) {
        Student student = getActiveStudent(studentId);
        Course course = courseService.getActiveCourse(courseId);
//        student.getCourses().remove(course);
        studentRepository.save(student);
    }
}
