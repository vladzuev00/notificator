package by.aurorasoft.notificator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

import static java.time.LocalDateTime.ofInstant;
import static java.util.Objects.requireNonNullElseGet;

@Value
public class Schedule {
    private static final int SECONDS_IN_DAY = 86400;
    private static final int DAYS_IN_WEEK = 7;

    public static Schedule FULL_WEEK = new Schedule(List.of(new Range(0, SECONDS_IN_DAY * DAYS_IN_WEEK - 1)));

    List<Range> ranges;

    @JsonCreator
    public Schedule(@JsonProperty("range") final List<Range> ranges) {
        this.ranges = requireNonNullElseGet(ranges, LinkedList::new);
    }

    public boolean isFit(final Instant time, final ZoneId zoneId) {
        final LocalDateTime localDateTime = ofInstant(time, zoneId);
        final int dayIndex = localDateTime.getDayOfWeek().getValue();
        final int secondOfDay = localDateTime.toLocalTime().toSecondOfDay();
        final int secondOfWeek = ((dayIndex - 1) * SECONDS_IN_DAY) + secondOfDay;
        return ranges.stream().anyMatch(r -> r.isBelong(secondOfWeek));
    }

    @Value
    public static class Range {
        Integer from;
        Integer to;

        @JsonCreator
        public Range(@JsonProperty("from") final Integer from,
                     @JsonProperty("to") final Integer to) {
            this.from = from;
            this.to = to;
        }

        private boolean isBelong(final int value) {
            return from <= value && value <= to;
        }
    }
}

