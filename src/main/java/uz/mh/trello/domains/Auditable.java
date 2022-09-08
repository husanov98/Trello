package uz.mh.trello.domains;

import lombok.*;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
