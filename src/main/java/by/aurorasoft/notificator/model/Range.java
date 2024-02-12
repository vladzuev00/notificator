package by.aurorasoft.notificator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;

@Value
public class Range<T> implements Serializable {
    T from;
    T to;

    @JsonCreator
    public Range(@JsonProperty("from") final T from, @JsonProperty("to") final T to) {
        this.from = from;
        this.to = to;
    }
}
