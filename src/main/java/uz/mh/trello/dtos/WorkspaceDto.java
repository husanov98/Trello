package uz.mh.trello.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDto {

    private Long id;
    private String name;

    public WorkspaceDto(String name) {
        this.name = name;
    }
}
