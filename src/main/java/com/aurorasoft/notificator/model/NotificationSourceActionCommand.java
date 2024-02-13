package com.aurorasoft.notificator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;

import static org.springframework.util.StringUtils.hasText;

@Value
public class NotificationSourceActionCommand implements Serializable {
    Action activate;
    Action deactivate;

    @JsonCreator
    public NotificationSourceActionCommand(@JsonProperty("activate") final Action activate,
                                           @JsonProperty("deactivate") final Action deactivate) {
        this.activate = nullIfNoText(activate);
        this.deactivate = nullIfNoText(deactivate);
    }

    private Action nullIfNoText(final Action action) {
        return action != null && hasText(action.text) ? action : null;
    }

    @Value
    public static class Action implements Serializable {
        String text;
        int timeout;

        @JsonCreator
        public Action(@JsonProperty("text") final String text,
                      @JsonProperty("timeout") final int timeout) {
            this.text = text;
            this.timeout = timeout;
        }
    }
}
