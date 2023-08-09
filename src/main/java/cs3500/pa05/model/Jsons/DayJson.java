package cs3500.pa05.model.Jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Weekday;
import java.util.List;

/**
 * @param weekday - weekday for the day
 * @param events - events in the day
 * @param tasks - tasks in the day
 * @param maxEvents - total events in the day
 * @param maxTasks - total tasks in the day
 */
public record DayJson(@JsonProperty("days-of-week") Weekday weekday,
                      @JsonProperty("events") List<EventJson> events,
                      @JsonProperty("tasks") List<TaskJson> tasks,
                      @JsonProperty("max-event") int maxEvents,
                      @JsonProperty("max-tasks") int maxTasks) {
}
