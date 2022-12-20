package ru.ddc.sbs.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_grade")
public class StudentGrade {

    @EmbeddedId
    private StudentGradeKey gradeKey;

    @ManyToOne(optional = false)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(optional = false)
    @MapsId("taskId")
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "grade", nullable = false)
    private Integer grade = 0;

    public StudentGradeKey getGradeKey() {
        return gradeKey;
    }

    public void setGradeKey(StudentGradeKey gradeKey) {
        this.gradeKey = gradeKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}