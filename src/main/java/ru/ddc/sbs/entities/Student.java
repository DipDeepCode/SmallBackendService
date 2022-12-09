package ru.ddc.sbs.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "group_number", nullable = false)
    private String groupNumber;

//    @ManyToMany
//    @JoinTable(name = "student_courses",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "courses_id"))
//    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<StudentData> studentDataList = new ArrayList<>();

    @Column(name = "entry_start_date", nullable = false)
    private LocalDateTime entryStartDate = LocalDateTime.now();

    @Column(name = "entry_end_date", nullable = false)
    private LocalDateTime entryEndDate = LocalDateTime.parse("9999-12-31T00:00:00");

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "link_to_previous_entry_id")
    private Student linkToPreviousEntry;

    @Override
    public Student clone() {
        try {
            Student clone = (Student) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}