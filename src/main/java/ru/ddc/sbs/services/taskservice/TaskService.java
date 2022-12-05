package ru.ddc.sbs.services.taskservice;

import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.AddEntityException;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    Task addNewTask(Long courseId, Task task) throws AddEntityException;
    List<Task> getAllActiveTasks(Long courseId);
    Task getActiveTask(Long courseId, Long taskId);
    Task updateActiveTask(Long courseId,
                          Long taskId,
                          String newName,
                          LocalDateTime newDeadline,
                          Integer newHighestMark) throws AddEntityException;
    void removeTask(Long courseId, Long taskId);
    List<Task> getTaskHistory(Long courseId, Long taskId);
}
