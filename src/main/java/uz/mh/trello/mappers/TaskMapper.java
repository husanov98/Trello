package uz.mh.trello.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.mh.trello.domains.Task;
import uz.mh.trello.dtos.TaskCreateDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    List<TaskCreateDto> toDto(List<Task> tasks);

    TaskCreateDto toDto(Task task);

    Task fromCreateDto(TaskCreateDto taskCreateDto);
}
