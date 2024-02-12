package by.aurorasoft.notificator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class Schedule<T> implements Serializable {
    List<T> ranges;

    @JsonCreator
    public Schedule(final List<T> ranges) {
        this.ranges = ranges;
    }
}
