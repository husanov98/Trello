package uz.mh.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.trello.domains.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
