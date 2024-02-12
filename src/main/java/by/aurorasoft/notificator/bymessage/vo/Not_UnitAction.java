package by.aurorasoft.notificator.bymessage.vo;

import com.locator.server.domain.entity.ActionType;
import lombok.Value;

import java.time.Instant;

@Value
public class Not_UnitAction {

    public static final String MESSAGE_CONNECTION_REJECTED = "connection rejected";
    public static final String MESSAGE_CONNECTED = "connected";
    public static final String MESSAGE_DISCONNECTED = "disconnected";
    public static final String MESSAGE_RECEIVED = "message received";
    public static final String MESSAGE_SKIP = "message skip";

    long unitId;
    String type;
    String message;
    long timeSeconds = Instant.now().getEpochSecond();

    public static Not_UnitAction info(long unitId, String message) {
        return new Not_UnitAction(unitId, ActionType.INFO.toString(), message);
    }

    public static Not_UnitAction warn(long unitId, String message) {
        return new Not_UnitAction(unitId, ActionType.WARN.toString(), message);
    }

    public static Not_UnitAction error(long unitId, String message) {
        return new Not_UnitAction(unitId, ActionType.ERROR.toString(), message);
    }
}
