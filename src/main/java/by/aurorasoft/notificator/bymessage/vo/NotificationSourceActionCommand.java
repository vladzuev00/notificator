package by.aurorasoft.notificator.bymessage.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NotificationSourceActionCommand {

    Action activate;
    Action deactivate;

    @JsonCreator
    public NotificationSourceActionCommand(@JsonProperty("activate") Action activate,
                                           @JsonProperty("deactivate") Action deactivate) {
        this.activate = activate;
        this.deactivate = deactivate;
    }

    @Value
    public static class Action {
        String text;
        int timeout;

        @JsonCreator
        public Action(@JsonProperty("text") String text,
                      @JsonProperty("timeout") int timeout) {
            this.text = text;
            this.timeout = timeout;
        }
    }
}
