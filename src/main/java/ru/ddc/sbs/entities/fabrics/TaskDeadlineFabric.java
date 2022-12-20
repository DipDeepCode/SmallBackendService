package ru.ddc.sbs.entities.fabrics;

import org.springframework.stereotype.Component;
import ru.ddc.sbs.entities.Course;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.entities.TaskDeadline;
import ru.ddc.sbs.entities.TaskDeadlineKey;

import java.time.LocalDateTime;

@Component
public class TaskDeadlineFabric {
    public TaskDeadline getEntity(Course course, Task task, LocalDateTime deadline) {
        TaskDeadlineKey taskDeadlineKey = new TaskDeadlineKey();
        taskDeadlineKey.setCourseId(course.getId());
        taskDeadlineKey.setTaskId(task.getId());
        TaskDeadline taskDeadline = new TaskDeadline();
        taskDeadline.setTaskDeadlineKey(taskDeadlineKey);
        taskDeadline.setCourse(course);
        taskDeadline.setTask(task);
        taskDeadline.setDeadline(deadline);
        return taskDeadline;
    }
}
