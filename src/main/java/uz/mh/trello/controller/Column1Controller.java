package uz.mh.trello.controller;


import org.springframework.web.bind.annotation.*;
import uz.mh.trello.dtos.ColumnCreateDto;
import uz.mh.trello.response.ApiResponse;
import uz.mh.trello.services.Column1Service;

import java.util.List;

@RestController
public class Column1Controller extends ApiController<Column1Service>{

    protected Column1Controller(Column1Service service) {
        super(service);
    }

    @GetMapping(value = PATH + "/column")
    public ApiResponse<List<ColumnCreateDto>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @GetMapping(value = PATH + "/column/{id}")
    public ApiResponse<ColumnCreateDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @PostMapping(value = PATH + "/column",produces = "application/json")
    public ApiResponse<String> createOrUpdate(@RequestBody ColumnCreateDto dto) {
        service.createOrUpdate(dto);
        return new ApiResponse<>("created");
    }

    @DeleteMapping(value = PATH + "/column/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>();
    }
}
