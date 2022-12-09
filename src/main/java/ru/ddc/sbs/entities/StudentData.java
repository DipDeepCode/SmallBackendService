package ru.ddc.sbs.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "student_data")
public class StudentData {
    @EmbeddedId
    private StudentDataKey studentDataKey;

//    @ManyToOne
//    @MapsId
//    @JoinColumn(name = "student_id")
//    private Student student;

//    @ManyToOne
//    @MapsId
//    @JoinColumn(name = "course_id")
//    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "is_credited")
    private Boolean isCredited;
}