package cs3500.pa05.model.Jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @param weekName
 * @param days
 * @param maxEvents
 * @param maxTasks
 */
public record WeekJson(@JsonProperty("name") String weekName,
                       @JsonProperty("days") List<DayJson> days,
                       @JsonProperty("max-events") int maxEvents,
                       @JsonProperty("max-tasks") int maxTasks,
                       @JsonProperty("notes") String notes,
                       @JsonProperty("quotes") String quotes,
                       @JsonProperty("events") List<EventJson> events,
                       @JsonProperty("tasks")List<TaskJson> tasks) {
}
