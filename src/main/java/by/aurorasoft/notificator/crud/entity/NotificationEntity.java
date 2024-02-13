package by.aurorasoft.notificator.crud.entity;

import by.aurorasoft.notificator.model.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.Instant;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "notification")
public class NotificationEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "unit_id")
    private UnitEntity unit;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "notification_source_id")
    private NotificationSourceEntity source;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "finish_time")
    private Instant finishTime;

    @Enumerated(STRING)
    @Column(name = "status")
    @JdbcTypeCode(NAMED_ENUM)
    private NotificationStatus status;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "created_time")
    private Instant createdTime;
}
