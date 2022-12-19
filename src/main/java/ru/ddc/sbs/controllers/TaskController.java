package ru.ddc.sbs.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.TaskDto;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.PersistError;
import ru.ddc.sbs.services.task.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final CustomMapper customMapper;

    public TaskController(TaskService taskService,
                          CustomMapper customMapper) {
        this.taskService = taskService;
        this.customMapper = customMapper;
    }

    @PostMapping("/addTask")
    public TaskDto addTask(@RequestBody TaskDto taskDto) throws PersistError {
        Task requestBodyTask = customMapper.map(taskDto, Task.class);
        Task addedTask = taskService.addTask(requestBodyTask);
        return customMapper.map(addedTask, TaskDto.class);
    }

    @GetMapping("/findAllTasks")
    public List<TaskDto> findAllTasks() {
        List<Task> allTasks = taskService.findAllTasks();
        return customMapper.mapList(allTasks, TaskDto.class);
    }

    @GetMapping("/findTaskById")
    public TaskDto findTaskById(@RequestParam Long taskId) {
        Task taskById = taskService.findTaskById(taskId);
        return customMapper.map(taskById, TaskDto.class);
    }

    @GetMapping("/findTaskByName")
    public TaskDto findTaskByName(@RequestParam String taskName) {
        Task taskByName = taskService.findTaskByName(taskName);
        return customMapper.map(taskByName, TaskDto.class);
    }

    @PutMapping("/updateTaskById")
    public void updateTaskById(@RequestParam String newTaskName,
                               @RequestParam Integer newHighestGrade,
                               @RequestParam Long taskId) {
        taskService.updateTaskById(newTaskName, newHighestGrade, taskId);
    }
}
