package ru.ddc.sbs.entities;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "highest_grade", nullable = false)
    private Integer highestGrade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHighestGrade() {
        return highestGrade;
    }

    public void setHighestGrade(Integer highestGrade) {
        this.highestGrade = highestGrade;
    }
}