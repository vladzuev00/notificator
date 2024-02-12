package by.aurorasoft.notificator.bymessage.vo;

import lombok.Data;

@Data
public class Not_NotificationSourceLight {
    private final Long id;
    private int pendingSeconds;

    public Not_NotificationSourceLight(Long id, int pendingSeconds) {
        this.id = id;
        this.pendingSeconds = check(pendingSeconds);
    }

    public void setPendingSeconds(int pendingSeconds) {
        this.pendingSeconds = check(pendingSeconds);
    }

    private int check(int pendingSeconds){
        if (pendingSeconds < 0) {
            pendingSeconds = 0;
        }
        if (pendingSeconds > 3600) {
            pendingSeconds = 3600;
        }
        return pendingSeconds;
    }
}
