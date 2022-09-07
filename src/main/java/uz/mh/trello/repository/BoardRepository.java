package uz.mh.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.trello.domains.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
