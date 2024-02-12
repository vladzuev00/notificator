package by.aurorasoft.notificator.bymessage.vo;

import com.locator.server.domain.dto.command.CommandStatus;
import com.locator.server.domain.entity.command.CommandType;
import com.locator.server.domain.entity.command.ProviderType;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.Objects;

@Data
public class Command {
    private long id;
    private CommandStatus status;
    private final long unitId;
    private final String text;
    private final CommandType commandType;
    private ProviderType providerType;
    private int attempt = 0;

    /**
     * таймаут отправки сообщения, если 0 отправить в любом случае
     */
    private final int lifeTimeout;

    /**
     * time when command was receive or sent
     */
    @Nullable
    private Instant actualTime;

    private Instant createdTime;

    public boolean isAlive() {
        return lifeTimeout == 0 || createdTime.plusSeconds(lifeTimeout).isAfter(Instant.now());
    }

    public Command(long id,
                   CommandStatus status,
                   long unitId,
                   String text,
                   CommandType commandType,
                   ProviderType providerType,
                   @Nullable Instant actualTime,
                   Instant createdTime,
                   int lifeTimeout) {
        this.id = id;
        this.status = status;
        this.unitId = unitId;
        this.text = text;
        this.commandType = commandType;
        this.actualTime = actualTime == null || actualTime.getEpochSecond() == 0 ? null : actualTime;
        this.lifeTimeout = lifeTimeout;
        this.providerType = providerType == null ? ProviderType.COMMON : providerType;
        this.createdTime = createdTime == null ? Instant.now() : createdTime;
    }

    public Command(long id, CommandStatus status, long unitId, String text, CommandType commandType, ProviderType providerType, int lifeTimeout) {
        this(id, status, unitId, text, commandType, providerType, null, Instant.now(), lifeTimeout);
    }

    public Command(long id, CommandStatus status, long unitId, String text, CommandType commandType, ProviderType providerType) {
        this(id, status, unitId, text, commandType, providerType, null, Instant.now(), 0);
    }

    public int increaseAttempt() {
        return ++attempt;
    }

    public static Command of(long unitId, Not_NotificationSourceActionCommand.Action action, Instant createdTime) {
        return new Command(0, CommandStatus.SENDING, unitId, action.getText(), CommandType.TCP_COMMAND, ProviderType.NOTIFICATION_ACTION, null, createdTime, action.getTimeout());
    }

    public static Command tcpAnswer(long unitId, String text) {
        return new Command(0L, CommandStatus.SENT, unitId, text, CommandType.TCP_ANSWER, ProviderType.COMMON, Instant.now(), Instant.now(), 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return getId() == command.getId() && getUnitId() == command.getUnitId() && getText().equals(command.getText()) && getCommandType() == command.getCommandType() && getProviderType() == command.getProviderType() && getCreatedTime().equals(command.getCreatedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUnitId(), getText(), getCommandType(), getProviderType(), getCreatedTime());
    }
}

