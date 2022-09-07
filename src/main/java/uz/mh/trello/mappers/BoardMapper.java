package uz.mh.trello.mappers;

import org.mapstruct.Mapper;
import uz.mh.trello.domains.Board;
import uz.mh.trello.dtos.BoardCreateDto;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BoardMapper {
    List<BoardCreateDto> toDto(List<Board> boards);

    BoardCreateDto toDto(Board board);

    Board fromCreateDto(BoardCreateDto boardCreateDto);
}
