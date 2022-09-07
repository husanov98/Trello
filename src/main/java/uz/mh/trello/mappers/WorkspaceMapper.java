package uz.mh.trello.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.mh.trello.domains.Workspace;
import uz.mh.trello.dtos.WorkspaceDto;

@Mapper(componentModel = "spring")
@Component
public interface WorkspaceMapper {
    WorkspaceDto toDto(Workspace workspace);

    Workspace fromDto(WorkspaceDto workspaceDto);
}
