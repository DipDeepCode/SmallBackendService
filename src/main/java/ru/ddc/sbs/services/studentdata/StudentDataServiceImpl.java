package ru.ddc.sbs.services.studentdata;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Student;
import ru.ddc.sbs.entities.StudentData;
import ru.ddc.sbs.entities.StudentGrade;
import ru.ddc.sbs.entities.fabrics.StudentDataFabric;
import ru.ddc.sbs.repositories.StudentDataRepository;
import ru.ddc.sbs.services.course.CourseService;
import ru.ddc.sbs.services.ratingcalculation.StudentDataCalculation;
import ru.ddc.sbs.services.student.StudentService;

import java.util.List;

@Service
public class StudentDataServiceImpl implements StudentDataService {
    private final StudentDataRepository studentDataRepository;
    private final CourseService courseService;
    private final StudentService studentService;
    private final StudentDataFabric studentDataFabric;
    private final StudentDataCalculation studentDataCalculation;

    public StudentDataServiceImpl(StudentDataRepository studentDataRepository,
                                  @Lazy CourseService courseService,
                                  @Lazy StudentService studentService,
                                  StudentDataFabric studentDataFabric,
                                  StudentDataCalculation studentDataCalculation) {
        this.studentDataRepository = studentDataRepository;
        this.courseService = courseService;
        this.studentService = studentService;
        this.studentDataFabric = studentDataFabric;
        this.studentDataCalculation = studentDataCalculation;
    }

    @Override
    public void linkCourseToStudent(Long courseId, Long studentId) {
        Course course = courseService.findCourseById(courseId);
        Student student = studentService.findStudentById(studentId);
        StudentData studentData = studentDataFabric.getEntity(course, student, 0.0d, false);
        studentDataRepository.save(studentData);
    }

    @Override
    public StudentData findStudentData(Long courseId, Long studentId) {
        return studentDataRepository.findByStudentDataKey_StudentIdAndStudentDataKey_CourseId(studentId, courseId).orElseThrow();
    }

    @Override
    public List<StudentData> findStudentDataByStudentId(Long studentId) {
        return studentDataRepository.findByStudentDataKey_StudentId(studentId);//studentDataList;
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) {
        List<StudentData> studentDataList = studentDataRepository.findByStudentDataKey_CourseId(courseId);
        return studentDataList.stream()
                .map(StudentData::getStudent)
                .toList();
    }

    @Override
    public List<Course> findCoursesByStudentId(Long studentId) {
        List<StudentData> courseList = studentDataRepository.findByStudentDataKey_StudentId(studentId);
        return courseList.stream()
                .map(StudentData::getCourse)
                .toList();
    }

    @Transactional
    @Override
    public void unlinkCourseFromStudent(Long studentId, Long courseId) {
        studentDataRepository.deleteByStudentDataKey_StudentIdAndStudentDataKey_CourseId(studentId, courseId);
    }

    @Override
    public Boolean isStudentExistInCourse(Long studentId, Long courseId) {
        return studentDataRepository.existsByStudentDataKey_StudentIdAndStudentDataKey_CourseId(studentId, courseId);
    }

    @Override
    public void calculateStudentData(Long studentId, Long courseId, List<StudentGrade> studentGradeList) {
        StudentData studentData = findStudentData(courseId, studentId);
        Double rating = studentDataCalculation.calculateRating(studentGradeList);
        Boolean isCredited = studentDataCalculation.calculateCredited(rating);
        studentData.setRating(rating);
        studentData.setCredited(isCredited);
        studentDataRepository.save(studentData);
    }
}
