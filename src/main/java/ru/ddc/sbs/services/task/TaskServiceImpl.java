package ru.ddc.sbs.services.task;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.PersistError;
import ru.ddc.sbs.repositories.TaskRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) throws PersistError {
        try {
            return taskRepository.saveAndFlush(task);
        } catch (DataIntegrityViolationException e) {
            throw new PersistError("Задание с таким названием уже существует");
        }
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAllByOrderByName();
    }

    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Задание c id = " + taskId + " не найдено"));
    }

    @Override
    public Task findTaskByName(String taskName) {
        return taskRepository.findByName(taskName).orElseThrow(() -> new EntityNotFoundException("Задание с именем " + taskName + " не найдено"));
    }

    @Override
    public void updateTaskById(String newTaskName, Integer newHighestGrade, Long taskId) {
        taskRepository.updateTaskById(newTaskName, newHighestGrade, taskId);
    }
}
