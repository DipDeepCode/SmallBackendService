package ru.ddc.sbs.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_deadline")
public class TaskDeadline {
    @EmbeddedId
    private TaskDeadlineKey taskDeadlineKey;

    @ManyToOne(optional = false)
    @MapsId("taskId")
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(optional = false)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    public TaskDeadlineKey getTaskDeadlineKey() {
        return taskDeadlineKey;
    }

    public void setTaskDeadlineKey(TaskDeadlineKey taskDeadlineKey) {
        this.taskDeadlineKey = taskDeadlineKey;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}