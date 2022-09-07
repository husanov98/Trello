package uz.mh.trello.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCreateDto {

    private Long id;

    private String name;

    private Long workspaceId;
}
