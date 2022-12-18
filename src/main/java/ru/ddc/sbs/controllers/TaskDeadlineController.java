package ru.ddc.sbs.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.ddc.sbs.custommapper.CustomMapper;
import ru.ddc.sbs.dtos.TaskDeadlineDto;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.services.taskdeadline.TaskDeadlineService;

import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("/linkTaskToCourse")
    public void linkTaskToCourse(@RequestParam Long courseId,
                                 @RequestParam Long taskId,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline) throws ApiError {
        taskDeadlineService.linkTaskToCourse(courseId, taskId, deadline);
    }

    @GetMapping("/findTask")
    public TaskDeadlineDto findTask(@RequestParam Long taskId,
                                    @RequestParam Long courseId) {
        return customMapper.map(taskDeadlineService.findTask(courseId, taskId), TaskDeadlineDto.class);
    }

    @GetMapping("/findTasksByCourseId")
    public List<TaskDeadlineDto> findTasksByCourseId(@RequestParam Long courseId) {
        return customMapper.mapList(taskDeadlineService.findTasksByCourseId(courseId), TaskDeadlineDto.class);
    }

    @PutMapping("/updateDeadline")
    public void updateDeadline(@RequestParam Long courseId,
                               @RequestParam Long taskId,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline) throws ApiError {
        taskDeadlineService.linkTaskToCourse(courseId, taskId, deadline);
    }

    @DeleteMapping("/unlinkTaskFromCourse")
    public void unlinkTaskFromCourse(@RequestParam Long taskId,
                                     @RequestParam Long courseId) {
        taskDeadlineService.unlinkTaskFromCourse(taskId, courseId);
    }
}
