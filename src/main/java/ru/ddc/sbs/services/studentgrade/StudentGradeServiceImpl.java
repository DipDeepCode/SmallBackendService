package ru.ddc.sbs.services.studentgrade;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.*;
import ru.ddc.sbs.entities.fabrics.StudentGradeFabric;
import ru.ddc.sbs.exceptions.PersistException;
import ru.ddc.sbs.repositories.StudentGradeRepository;
import ru.ddc.sbs.services.course.CourseService;
import ru.ddc.sbs.services.student.StudentService;
import ru.ddc.sbs.services.studentdata.StudentDataService;
import ru.ddc.sbs.services.task.TaskService;
import ru.ddc.sbs.services.taskdeadline.TaskDeadlineService;

import java.util.List;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;
    private final StudentService studentService;
    private final CourseService courseService;
    private final TaskService taskService;
    private final StudentGradeFabric studentGradeFabric;
    private final TaskDeadlineService taskDeadlineService;
    private final StudentDataService studentDataService;

    public StudentGradeServiceImpl(StudentGradeRepository studentGradeRepository,
                                   @Lazy StudentService studentService,
                                   @Lazy CourseService courseService,
                                   @Lazy TaskService taskService,
                                   StudentGradeFabric studentGradeFabric,
                                   @Lazy TaskDeadlineService taskDeadlineService,
                                   @Lazy StudentDataService studentDataService) {
        this.studentGradeRepository = studentGradeRepository;
        this.studentService = studentService;
        this.courseService = courseService;
        this.taskService = taskService;
        this.studentGradeFabric = studentGradeFabric;
        this.taskDeadlineService = taskDeadlineService;
        this.studentDataService = studentDataService;
    }

    @Override
    public void addGrade(Long studentId, Long courseId, Long taskId, Integer grade) throws PersistException {
        Course course = courseService.findCourseById(courseId);
        Student student = studentService.findStudentById(studentId);
        Task task = taskService.findTaskById(taskId);
        if (!taskDeadlineService.isTaskExistsInCourse(taskId, courseId)) {
            throw new PersistException("Задание отсутствует в курсе");
        }
        if (!studentDataService.isStudentExistInCourse(studentId, courseId)) {
            throw new PersistException("Студент не подписан на этот курс");
        }
        if (grade > task.getHighestGrade()) {
            throw new PersistException("Оценка превышает максимально возможную");
        }
        StudentGrade studentGrade = studentGradeFabric.getEntity(student, course, task, grade);
        studentGradeRepository.save(studentGrade);
        studentDataService.calculateStudentData(studentId, courseId, getGrades(studentId, courseId));
    }

    @Override
    public List<StudentGrade> getGrades(Long studentId, Long courseId) {
        return studentGradeRepository.findByGradeKey_StudentIdAndGradeKey_CourseId(studentId, courseId);
    }
}
