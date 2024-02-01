package by.aurorasoft.notificator.crud.entity;

import by.aurorasoft.notificator.crud.entity.AbstractEntity;
import by.aurorasoft.notificator.model.NotificationEnablingState;
import by.aurorasoft.notificator.model.NotificationType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.converter.spi.EnumValueConverter;

import java.util.Map;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;
import static org.hibernate.type.SqlTypes.JSON;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;
import static org.postgresql.core.Oid.JSONB;

@Setter
@Getter
@ToString
@Entity
@Cacheable
@Cache(usage = READ_WRITE)
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
    @JdbcTypeCode(NAMED_ENUM)
    @Column(name = "enabling_state")
    private NotificationEnablingState enablingState;

    @Enumerated(STRING)
    @JdbcTypeCode(NAMED_ENUM)
    @Column(name = "enabling_state")
    private NotificationType type;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "message")
    private String message;

    @JdbcTypeCode(JSONB)
    @Column(name = "params")
    private Map<String, Object> params;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "action_command")
    NotificationSource.ActionCommand actionCommand;

    @Column(name = "push_enabled")
    private boolean pushEnabled;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @Cache(usage = READ_WRITE)
    @JoinTable(name = "notification_source_telegram",
            joinColumns = {@JoinColumn(name = "notification_source_id")},
            inverseJoinColumns = {@JoinColumn(name = "telegram_id")}
    )
    @ToString.Exclude
    private List<TelegramChatEntity> telegramChats;

    private int pendingSeconds;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "time_filter")
    private Schedule<Range<Integer>> timeFilter = ScheduleUtil.FULL_WEEK_SECONDS_FILTER;

    @Cache(usage = READ_WRITE)
    @OneToMany

    @JoinTable(name = "notification_source_geofence",
            joinColumns = {@JoinColumn(name = "notification_source_id")},
            inverseJoinColumns = {@JoinColumn(name = "geofence_id")})
    @ToString.Exclude
    private List<GeofenceEntity> geofences;

    @Cache(usage = READ_WRITE)
    @OneToMany
    @JoinTable(name = "notification_source_unit",
            joinColumns = {@JoinColumn(name = "notification_source_id")},
            inverseJoinColumns = {@JoinColumn(name = "unit_id")})
    @ToString.Exclude
    private List<UnitEntity> units;

    @Column(name = "color")
    private String color;

    public void setActionCommand(NotificationSource.ActionCommand actionCommand) {
        if (actionCommand == null || (actionCommand.getActivate() == null && actionCommand.getDeactivate() == null)) {
            this.actionCommand = null;
        } else {
            this.actionCommand = actionCommand;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotificationSourceEntity that = (NotificationSourceEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
