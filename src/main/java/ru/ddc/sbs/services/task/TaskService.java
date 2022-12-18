package ru.ddc.sbs.services.task;

import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.PersistError;

import java.util.List;

public interface TaskService {

    Task addTask(Task task) throws PersistError;
    List<Task> findAllTasks();
    Task findTaskById(Long taskId);
    Task findTaskByName(String taskName);
    void updateTaskById(String newTaskName, Integer newHighestGrade, Long taskId);
}
