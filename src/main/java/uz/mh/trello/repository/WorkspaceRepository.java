package uz.mh.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mh.trello.domains.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace,Long> {

}
