package uz.mh.trello.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnCreateDto {
    private Long id;
    private String name;
    private Long boardId;
}
