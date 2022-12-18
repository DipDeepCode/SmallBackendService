package ru.ddc.sbs.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_data")
public class StudentData {
    @EmbeddedId
    private StudentDataKey studentDataKey;

    @ManyToOne(optional = false)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "is_credited", nullable = false)
    private Boolean isCredited = false;

    public StudentDataKey getStudentDataKey() {
        return studentDataKey;
    }

    public void setStudentDataKey(StudentDataKey studentDataKey) {
        this.studentDataKey = studentDataKey;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getCredited() {
        return isCredited;
    }

    public void setCredited(Boolean credited) {
        isCredited = credited;
    }
}