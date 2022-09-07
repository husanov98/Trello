package uz.mh.trello.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mh.trello.domains.Board;
import uz.mh.trello.domains.Column1;
import uz.mh.trello.dtos.ColumnCreateDto;
import uz.mh.trello.mappers.Column1Mapper;
import uz.mh.trello.repository.BoardRepository;
import uz.mh.trello.repository.Column1Repository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class Column1Service {
    private final Column1Repository repository;

    private final Column1Mapper mapper;

    private final BoardRepository boardRepository;

    public List<ColumnCreateDto> getAll() {
        List<Column1> column1List = repository.findAll();
        return mapper.toDto(column1List);
    }

    public ColumnCreateDto get(Long id) {
        Column1 column1 = repository.findById(id).get();
        return mapper.toDto(column1);
    }

    public void createOrUpdate(ColumnCreateDto dto) {
        if (dto.getId() == 0) {
            Column1 column1 = mapper.fromCreateDto(dto);
            Optional<Board> board = boardRepository.findById(dto.getBoardId());
            column1.setBoard(board.get());
            repository.save(column1);
        } else {
            Optional<Column1> optionalColumn = repository.findById(dto.getId());
            Column1 column1 = optionalColumn.get();
            column1.setName(dto.getName());
            Optional<Board> board = boardRepository.findById(dto.getBoardId());
            column1.setBoard(board.get());
            repository.save(column1);
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
