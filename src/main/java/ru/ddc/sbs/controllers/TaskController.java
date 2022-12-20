package ru.ddc.sbs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.TaskDto;
import ru.ddc.sbs.entities.Task;
import ru.ddc.sbs.exceptions.PersistError;
import ru.ddc.sbs.services.task.TaskService;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name = "TaskController",
        description = "Обеспечивает работу с заданиями"
)
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

    @Operation(
            summary = "Добавление задания в базу данных",
            description = "Добавляет задание (название, максимальный балл)"
    )
    @PostMapping("/addTask")
    public TaskDto addTask(@Valid @RequestBody TaskDto taskDto) throws PersistError {
        Task requestBodyTask = customMapper.map(taskDto, Task.class);
        Task addedTask = taskService.addTask(requestBodyTask);
        return customMapper.map(addedTask, TaskDto.class);
    }

    @Operation(
            summary = "Получение списка всех заданий",
            description = "Возвращает список всех заданий отсортированный по названию"
    )
    @GetMapping("/findAllTasks")
    public List<TaskDto> findAllTasks() {
        List<Task> allTasks = taskService.findAllTasks();
        return customMapper.mapList(allTasks, TaskDto.class);
    }

    @Operation(
            summary = "Поиск задания по его идентификатору",
            description = "Возвращает задание найденное по его идентификатору"
    )
    @GetMapping("/findTaskById")
    public TaskDto findTaskById(@RequestParam @Parameter(description = "id задания")Long taskId) {
        Task taskById = taskService.findTaskById(taskId);
        return customMapper.map(taskById, TaskDto.class);
    }

    @Operation(
            summary = "Поиск задания по его названию",
            description = "Возвращает задание найденное по его названию"
    )
    @GetMapping("/findTaskByName")
    public TaskDto findTaskByName(@RequestParam @Parameter(description = "Название задания") String taskName) {
        Task taskByName = taskService.findTaskByName(taskName);
        return customMapper.map(taskByName, TaskDto.class);
    }

    @Operation(
            summary = "Изменение данных задания",
            description = "Позволяет изменить название задания и максимальный балл"
    )
    @PutMapping("/updateTaskById")
    public void updateTaskById(@RequestParam @Parameter(description = "Новое название задания") String newTaskName,
                               @RequestParam @Parameter(description = "Новый максимальный балл") Integer newHighestGrade,
                               @RequestParam @Parameter(description = "id изменяемого задания") Long taskId) {
        taskService.updateTaskById(newTaskName, newHighestGrade, taskId);
    }
}
