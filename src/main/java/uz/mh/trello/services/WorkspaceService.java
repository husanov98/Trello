package uz.mh.trello.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.mh.trello.domains.Workspace;
import uz.mh.trello.dtos.WorkspaceDto;
import uz.mh.trello.mappers.WorkspaceMapper;
import uz.mh.trello.repository.WorkspaceRepository;
import uz.mh.trello.response.ApiResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    private final WorkspaceMapper workspaceMapper;

    @PreAuthorize("hasAuthority(T(uz.mh.trello.enums.Permissions).ROLE_CREATE)")
    public WorkspaceDto createOrUpdate(@RequestBody WorkspaceDto workspaceDto) {
        if (workspaceDto.getId() == null) {
            Workspace workspace = Workspace.builder()
                    .name(workspaceDto.getName())
                    .build();
            workspaceRepository.save(workspace);
        } else {
            Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceDto.getId());
            optionalWorkspace.get().setName(workspaceDto.getName());
            workspaceRepository.save(optionalWorkspace.get());
        }
        return new WorkspaceDto(workspaceDto.getName());
    }
    @PreAuthorize("hasAuthority(T(uz.mh.trello.enums.Permissions).ROLE_CREATE)")
    public WorkspaceDto getOne(Long workspaceId) {
        Optional<Workspace> workspace = workspaceRepository.findById(workspaceId);
        return workspaceMapper.toDto(workspace.get());
    }
    @PreAuthorize("hasAuthority(T(uz.mh.trello.enums.Permissions).ROLE_CREATE)")
    public String deleteById(Long workspaceId) {
        workspaceRepository.deleteById(workspaceId);
        return "workspace deleted";
    }
}
