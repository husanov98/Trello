package uz.mh.trello.controller;

import org.springframework.web.bind.annotation.*;

import uz.mh.trello.dtos.BoardCreateDto;
import uz.mh.trello.response.ApiResponse;
import uz.mh.trello.services.BoardService;

import java.util.List;

@RestController
public class BoardController extends ApiController<BoardService> {

    protected BoardController(BoardService service) {
        super(service);
    }

    @GetMapping(value = PATH + "/board")
    public ApiResponse<List<BoardCreateDto>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @GetMapping(value = PATH + "/board/{id}")
    public ApiResponse<BoardCreateDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @PostMapping(value = PATH + "/board",produces = "application/json")
    public ApiResponse<String> createOrUpdate(@RequestBody BoardCreateDto boardCreateDto) {
        service.createOrUpdate(boardCreateDto);
        return new ApiResponse<>("created");
    }

    @DeleteMapping(value = PATH + "/board/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>();
    }
}
