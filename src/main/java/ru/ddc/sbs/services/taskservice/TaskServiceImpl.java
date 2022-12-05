package ru.ddc.sbs.services.taskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.AddEntityException;
import ru.ddc.sbs.repositories.TaskRepository;
import ru.ddc.sbs.services.courseservice.CourseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CourseService courseService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           @Lazy CourseService courseService) {
        this.taskRepository = taskRepository;
        this.courseService = courseService;
    }

    @Override
    public Task addNewTask(Long courseId, Task task) throws AddEntityException {
        //TODO добавить валидацию
        String name = task.getName();
        if (taskRepository.findByCourseIdAndNameAndIsActiveTrue(courseId, name).isEmpty()) {
            task.setCourse(courseService.getActiveCourse(courseId));
            return taskRepository.save(task);
        } else {
            throw new AddEntityException("Задание с таким названием уже существует");
        }
    }

    @Override
    public List<Task> getAllActiveTasks(Long courseId) {
        courseService.getActiveCourse(courseId);
        return taskRepository.findAllByCourseIdAndIsActiveTrue(courseId);
    }

    @Override
    public Task getActiveTask(Long courseId, Long taskId) {
        courseService.getActiveCourse(courseId);
        return taskRepository.findByCourseIdAndIdAndIsActiveTrue(courseId, taskId).orElseThrow();
    }

    @Transactional
    @Override
    public Task updateActiveTask(Long courseId,
                                 Long taskId,
                                 String newName,
                                 LocalDateTime newDeadline,
                                 Integer newHighestMark) throws AddEntityException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Task originalTask = getActiveTask(courseId, taskId);
        Task clonedTask = originalTask.clone();
        originalTask.setIsActive(false);
        originalTask.setEntryEndDate(currentDateTime);
        taskRepository.save(originalTask);
        clonedTask.setId(null);
        clonedTask.setEntryStartDate(currentDateTime);
        clonedTask.setName(newName);
        clonedTask.setHighestMark(newHighestMark);
        clonedTask.setLinkToPreviousEntry(originalTask);
        return addNewTask(courseId, clonedTask);
    }

    @Transactional
    @Override
    public void removeTask(Long courseId, Long taskId) {
        Task task = getActiveTask(courseId, taskId);
        task.setIsActive(false);
        task.setEntryEndDate(LocalDateTime.now());
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTaskHistory(Long courseId, Long taskId) {
        List<Task> taskHistory = new ArrayList<>();
        Task task = getActiveTask(courseId, taskId);
        do {
            taskHistory.add(task);
            task = task.getLinkToPreviousEntry();
        } while (task != null);
        return taskHistory;
    }
}
