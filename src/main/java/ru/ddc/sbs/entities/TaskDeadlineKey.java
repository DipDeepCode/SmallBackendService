package ru.ddc.sbs.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskDeadlineKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 6961366307750059708L;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDeadlineKey that = (TaskDeadlineKey) o;

        if (!Objects.equals(taskId, that.taskId)) return false;
        return Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}