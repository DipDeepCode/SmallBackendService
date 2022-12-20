package ru.ddc.sbs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.TaskDeadlineDto;
import ru.ddc.sbs.entities.TaskDeadline;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.services.taskdeadline.TaskDeadlineService;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "TaskDeadlineController",
        description = "Обеспечивает добавления задания в курс и установку дедлайна, удаление задание из курса, изменение дедлайна, поиск заданий"
)
@RestController
@RequestMapping("/taskDeadline")
public class TaskDeadlineController {
    private final TaskDeadlineService taskDeadlineService;
    private final CustomMapper customMapper;

    public TaskDeadlineController(TaskDeadlineService taskDeadlineService,
                                  CustomMapper customMapper) {
        this.taskDeadlineService = taskDeadlineService;
        this.customMapper = customMapper;
    }

    @Operation(
            summary = "Добавление задания в курс по id задания и id курса",
            description = "Объединяет задание и курс и устанавливает дедлайн"
    )
    @PostMapping("/linkTaskToCourse")
    public void linkTaskToCourse(@RequestParam @Parameter(description = "id курса") Long courseId,
                                 @RequestParam @Parameter(description = "id задания") Long taskId,
                                 @RequestParam @Parameter(description = "Дата и время дедлайна")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline) throws ApiError {
        taskDeadlineService.linkTaskToCourse(courseId, taskId, deadline);
    }

    @Operation(
            summary = "Поиск задания по id задания и id курса",
            description = "Возвращает курс, задание и его дедлайн на данном курсе"
    )
    @GetMapping("/findTask")
    public TaskDeadlineDto findTask(@RequestParam @Parameter(description = "id задания") Long taskId,
                                    @RequestParam @Parameter(description = "id курса") Long courseId) {
        TaskDeadline task = taskDeadlineService.findTask(courseId, taskId);
        return customMapper.map(task, TaskDeadlineDto.class);
    }

    @Operation(
            summary = "Поиск всех заданий по id курса",
            description = "Возвращает список всех заданий добавленных в данный курс"
    )
    @GetMapping("/findTasksByCourseId")
    public List<TaskDeadlineDto> findTasksByCourseId(@RequestParam @Parameter(description = "id курса") Long courseId) {
        List<TaskDeadline> tasksByCourseId = taskDeadlineService.findTasksByCourseId(courseId);
        return customMapper.mapList(tasksByCourseId, TaskDeadlineDto.class);
    }

    @Operation(
            summary = "Изменение дедлайна по id задания и id курса",
            description = "Изменяет дедлайн у задания на данном курсе"
    )
    @PutMapping("/updateDeadline")
    public void updateDeadline(@RequestParam @Parameter(description = "id курса") Long courseId,
                               @RequestParam @Parameter(description = "id задания") Long taskId,
                               @RequestParam @Parameter(description = "Новые дата и время дедлайна")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline) throws ApiError {
        taskDeadlineService.linkTaskToCourse(courseId, taskId, deadline);
    }

    @Operation(
            summary = "Исключение задание из курса по id задания и id курса",
            description = "Исключает задание из данного курса (дедлайн при этом теряется)"
    )
    @DeleteMapping("/unlinkTaskFromCourse")
    public void unlinkTaskFromCourse(@RequestParam @Parameter(description = "id задания") Long taskId,
                                     @RequestParam @Parameter(description = "id курса") Long courseId) {
        taskDeadlineService.unlinkTaskFromCourse(taskId, courseId);
    }
}
