package uz.mh.trello.controller;

import org.springframework.web.bind.annotation.*;
import uz.mh.trello.dtos.TaskCreateDto;
import uz.mh.trello.response.ApiResponse;
import uz.mh.trello.services.TaskService;

import java.util.List;

@RestController
public class TaskController extends ApiController<TaskService> {
    protected TaskController(TaskService service) {
        super(service);
    }

    @GetMapping(value = PATH + "/task")
    public ApiResponse<List<TaskCreateDto>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @GetMapping(value = PATH + "/taskById/{id}")
    public ApiResponse<TaskCreateDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @PostMapping(value = PATH + "/taskCreate",produces = "application/json")
    public ApiResponse<String> createOrUpdate(@RequestBody TaskCreateDto taskCreateDto) {
        service.createOrUpdate(taskCreateDto);
        return new ApiResponse<>("created");
    }

    @DeleteMapping(value = PATH + "/taskDelete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>();
    }
}
