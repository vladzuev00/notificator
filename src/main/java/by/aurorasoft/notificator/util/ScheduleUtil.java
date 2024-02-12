package by.aurorasoft.notificator.util;

import by.aurorasoft.notificator.model.Range;
import by.aurorasoft.notificator.model.Schedule;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public final class ScheduleUtil {
    public static Schedule<Range<Integer>> FULL_WEEK_SECONDS_FILTER = new Schedule<>(List.of(new Range<>(0, 86400 * 7 - 1)));
}
