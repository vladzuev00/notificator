package by.aurorasoft.notificator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;

import static org.springframework.util.StringUtils.hasText;

@Value
public class ActionCommand implements Serializable {
    Action activate;
    Action deactivate;

    @JsonCreator
    public ActionCommand(@JsonProperty("activate") final Action activate,
                         @JsonProperty("deactivate") final Action deactivate) {
        this.activate = nullifyIfNoText(activate);
        this.deactivate = nullifyIfNoText(deactivate);
    }

    private Action nullifyIfNoText(final Action action) {
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
