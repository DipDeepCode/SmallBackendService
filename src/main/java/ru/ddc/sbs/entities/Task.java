package ru.ddc.sbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "highest_mark", nullable = false)
    private Integer highestMark;

    @Column(name = "entry_start_date", nullable = false)
    private LocalDateTime entryStartDate = LocalDateTime.now();

    @Column(name = "entry_end_date", nullable = false)
    private LocalDateTime entryEndDate = LocalDateTime.parse("9999-12-31T00:00:00");

    @Column(name = "is_active", nullable = false)
    Boolean isActive = true;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "link_to_previous_entry_id")
    private Task linkToPreviousEntry;

    @Override
    public Task clone() {
        try {
            Task clone = (Task) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}