package by.aurorasoft.notificator.bymessage.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

@Value
public class Not_Schedule {

    public static Not_Schedule FULL_WEEK = new Not_Schedule(List.of(new Range(0, 86400 * 6 + 86399)));
    List<Range> ranges;

    @JsonCreator
    public Not_Schedule(@JsonProperty("range") List<Range> ranges) {
        this.ranges = ranges != null ? ranges : new LinkedList<>();
    }

    public boolean isFit(Instant time, ZoneId zoneId) {
        LocalDateTime ldt = LocalDateTime.ofInstant(time, zoneId);
        int dayIndex = ldt.getDayOfWeek().getValue();
        int secondOfDay = ldt.toLocalTime().toSecondOfDay();
        int secondOfWeek = ((dayIndex - 1) * 86400) + secondOfDay;
        return ranges.stream().anyMatch(r -> r.isFit(secondOfWeek));
    }

    @Value
    public static class Range {
        Integer from;
        Integer to;

        @JsonCreator
        public Range(@JsonProperty("from") Integer from,
                     @JsonProperty("to") Integer to) {
            this.from = from;
            this.to = to;
        }

        boolean isFit(int secondOfDay) {
            return secondOfDay >= from && secondOfDay <= to;
        }
    }
}
