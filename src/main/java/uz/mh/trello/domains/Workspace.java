package uz.mh.trello.domains;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private List<Board> boards;

    @ManyToMany
    private List<AuthUser> userList;

    @ManyToMany
    private List<AuthRole> roles;
}
