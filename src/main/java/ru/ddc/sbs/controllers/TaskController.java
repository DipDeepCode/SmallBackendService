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
        Task task = customMapper.map(taskDto, Task.class);
        return customMapper.map(taskService.addTask(task), TaskDto.class);
    }

    @GetMapping("/findAllTasks")
    public List<TaskDto> findAllTasks() {
        return customMapper.mapList(taskService.findAllTasks(), TaskDto.class);
    }

    @GetMapping("/findTaskById")
    public TaskDto findTaskById(@RequestParam Long taskId) {
        return customMapper.map(taskService.findTaskById(taskId), TaskDto.class);
    }

    @GetMapping("/findTaskByName")
    public TaskDto findTaskByName(@RequestParam String taskName) {
        return customMapper.map(taskService.findTaskByName(taskName), TaskDto.class);
    }

    @PutMapping("/updateTaskById")
    public void updateTaskById(@RequestParam String newTaskName,
                               @RequestParam Integer newHighestGrade,
                               @RequestParam Long taskId) {
        taskService.updateTaskById(newTaskName, newHighestGrade, taskId);
    }
}
