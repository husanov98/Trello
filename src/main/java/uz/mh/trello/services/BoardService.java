package uz.mh.trello.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mh.trello.domains.Board;

import uz.mh.trello.domains.Workspace;
import uz.mh.trello.dtos.BoardCreateDto;
import uz.mh.trello.mappers.BoardMapper;
import uz.mh.trello.repository.BoardRepository;
import uz.mh.trello.repository.WorkspaceRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    private final WorkspaceRepository workspaceRepository;

    public List<BoardCreateDto> getAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardMapper.toDto(boardList);
    }

    public BoardCreateDto get(Long id) {
        Board board = boardRepository.findById(id).get();
        return boardMapper.toDto(board);
    }

    public void createOrUpdate(BoardCreateDto boardCreateDto) {
        if (boardCreateDto.getId() == 0) {
            Board board = boardMapper.fromCreateDto(boardCreateDto);
            Optional<Workspace> workspace = workspaceRepository.findById(boardCreateDto.getWorkspaceId());
            board.setWorkspace(workspace.get());
            boardRepository.save(board);
        } else {
            Optional<Board> optionalBoard = boardRepository.findById(boardCreateDto.getId());
            Board board = optionalBoard.get();
            board.setName(boardCreateDto.getName());
            Optional<Workspace> workspace = workspaceRepository.findById(boardCreateDto.getWorkspaceId());
            board.setWorkspace(workspace.get());
            boardRepository.save(board);
        }
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
