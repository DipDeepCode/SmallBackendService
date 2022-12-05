package ru.ddc.sbs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.TaskDto;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.AddEntityException;
import ru.ddc.sbs.services.taskservice.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final CustomMapper customMapper;

    @Autowired
    public TaskController(TaskService taskService, CustomMapper customMapper) {
        this.taskService = taskService;
        this.customMapper = customMapper;
    }

    @PostMapping("/addNewTask")
    public TaskDto addNewTask(@RequestParam Long courseId,
                              @RequestBody TaskDto taskDto) throws AddEntityException {
        Task task = customMapper.map(taskDto, Task.class);
        return customMapper.map(taskService.addNewTask(courseId, task), TaskDto.class);
    }

    @GetMapping("/getAllActiveTasks")
    public List<TaskDto> getAllActiveTasks(@RequestParam Long courseId) {
        return customMapper.mapList(taskService.getAllActiveTasks(courseId), TaskDto.class);
    }

    @GetMapping("/getActiveTask")
    public TaskDto getActiveTask(@RequestParam Long courseId,
                                 @RequestParam Long taskId) {
        return customMapper.map(taskService.getActiveTask(courseId, taskId), TaskDto.class);
    }

    @PutMapping("/updateActiveTask")
    public TaskDto updateActiveTask(@RequestParam Long courseId,
                                    @RequestParam Long taskId,
                                    @RequestParam String newName,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDeadline,
                                    @RequestParam Integer newHighestMark) throws AddEntityException {
        return customMapper.map(taskService.updateActiveTask(courseId, taskId, newName, newDeadline, newHighestMark), TaskDto.class);
    }

    @GetMapping("/getTaskHistory")
    public List<TaskDto> getTaskHistory(@RequestParam Long courseId,
                                        @RequestParam Long taskId) {
        return customMapper.mapList(taskService.getTaskHistory(courseId, taskId), TaskDto.class);
    }

    @DeleteMapping("/removeTask")
    public void removeTask(@RequestParam Long courseId,
                           @RequestParam Long taskId) {
        taskService.removeTask(courseId, taskId);
    }
}
