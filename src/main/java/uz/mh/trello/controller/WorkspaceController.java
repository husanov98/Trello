package uz.mh.trello.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.mh.trello.domains.Workspace;
import uz.mh.trello.dtos.WorkspaceDto;
import uz.mh.trello.response.ApiResponse;
import uz.mh.trello.services.WorkspaceService;

@RestController
public class WorkspaceController extends ApiController<WorkspaceService> {

    protected WorkspaceController(WorkspaceService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/workspace/create", produces = "application/json")
    public ApiResponse<WorkspaceDto> createOrUpdateWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        return new ApiResponse<>(service.createOrUpdate(workspaceDto));
    }

    @GetMapping(value = PATH+"/workspace/{workspaceId}")
    public ApiResponse<WorkspaceDto> getOne(@PathVariable Long workspaceId){
        return new ApiResponse<>(service.getOne(workspaceId));
    }

    @DeleteMapping(value = PATH + "/workspace/{workspaceId}")
    public String deleteWorkspaceById(@PathVariable Long workspaceId){
        return service.deleteById(workspaceId);
    }
}
