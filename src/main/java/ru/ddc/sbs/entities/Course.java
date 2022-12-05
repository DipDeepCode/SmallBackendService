package ru.ddc.sbs.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @Column(name = "entry_start_date", nullable = false)
    private LocalDateTime entryStartDate = LocalDateTime.now();

    @Column(name = "entry_end_date", nullable = false)
    private LocalDateTime entryEndDate = LocalDateTime.parse("9999-12-31T00:00:00");

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "link_to_previous_entry_id")
    private Course linkToPreviousEntry;

    @Override
    public Course clone() {
        try {
            Course clone = (Course) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}