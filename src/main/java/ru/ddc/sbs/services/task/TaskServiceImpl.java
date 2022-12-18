package ru.ddc.sbs.services.task;

import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow();
    }

    @Override
    public Task findTaskByName(String taskName) {
        return taskRepository.findByName(taskName).orElseThrow();
    }

    @Override
    public void updateTaskById(String newTaskName, Integer newHighestGrade, Long taskId) {
        taskRepository.updateTaskById(newTaskName, newHighestGrade, taskId);
    }
}
