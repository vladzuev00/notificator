package by.aurorasoft.notificator.crud.entity;

import by.aurorasoft.notificator.model.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.Map;

import static by.aurorasoft.notificator.util.ScheduleUtil.FULL_WEEK_SECONDS_FILTER;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;
import static org.hibernate.type.SqlTypes.JSON;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Cacheable
@Cache(usage = READ_WRITE)
@Entity
@SQLRestriction("deleted = false")
@Table(name = "notification_source")
public class NotificationSourceEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(STRING)
    @Column(name = "enabling_state")
    @JdbcTypeCode(NAMED_ENUM)
    private NotificationEnablingState state;

    @Enumerated(STRING)
    @Column(name = "type")
    @JdbcTypeCode(NAMED_ENUM)
    private NotificationType type;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "message")
    private String message;

    @Column(name = "pending_seconds")
    private int pendingSeconds;

    @Column(name = "params")
    @JdbcTypeCode(JSON)
    private Map<String, Object> params;

    @Column(name = "action_command")
    @JdbcTypeCode(JSON)
    NotificationSourceActionCommand actionCommand;

    @Column(name = "push_enabled")
    private boolean pushEnabled;

    @Column(name = "color")
    private String color;

    @ManyToMany
    @Cache(usage = READ_WRITE)
    @JoinTable(name = "notification_source_telegram",
            joinColumns = {@JoinColumn(name = "notification_source_id")},
            inverseJoinColumns = {@JoinColumn(name = "telegram_id")}
    )
    @ToString.Exclude
    private List<TelegramChatEntity> telegramChats;

    @JdbcTypeCode(JSON)
    @Column(columnDefinition = "jsonb", name = "time_filter")
    private Schedule<Range<Integer>> timeFilter = FULL_WEEK_SECONDS_FILTER;

    @OneToMany
    @Cache(usage = READ_WRITE)
    @JoinTable(name = "notification_source_geofence",
            joinColumns = {@JoinColumn(name = "notification_source_id")},
            inverseJoinColumns = {@JoinColumn(name = "geofence_id")})
    @ToString.Exclude
    private List<GeofenceEntity> geofences;

    @OneToMany
    @Cache(usage = READ_WRITE)
    @JoinTable(name = "notification_source_unit",
            joinColumns = {@JoinColumn(name = "notification_source_id")},
            inverseJoinColumns = {@JoinColumn(name = "unit_id")})
    @ToString.Exclude
    private List<UnitEntity> units;
}
