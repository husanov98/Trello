package uz.mh.trello.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mh.trello.domains.Column1;
import uz.mh.trello.domains.Task;
import uz.mh.trello.dtos.TaskCreateDto;
import uz.mh.trello.mappers.TaskMapper;
import uz.mh.trello.repository.Column1Repository;
import uz.mh.trello.repository.TaskRepository;
import uz.mh.trello.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final Column1Repository columnRepository;

    public List<TaskCreateDto> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toDto(tasks);
    }

    public TaskCreateDto get(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return taskMapper.toDto(task.get());
    }

    public void createOrUpdate(TaskCreateDto taskCreateDto) {
        if (taskCreateDto.getId() == 0) {
            Task task = taskMapper.fromCreateDto(taskCreateDto);
            Optional<Column1> optionalColumn = columnRepository.findById(taskCreateDto.getColumnId());
            task.setColumn(optionalColumn.get());
            taskRepository.save(task);
        } else {
            Optional<Task> optionalTask = taskRepository.findById(taskCreateDto.getId());
            Task task = optionalTask.get();
            task.setName(taskCreateDto.getName());
            Optional<Column1> optionalColumn = columnRepository.findById(taskCreateDto.getColumnId());
            task.setColumn(optionalColumn.get());
            taskRepository.save(task);
        }
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
