package cs3500.pa05.model.Jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Weekday;
import java.time.LocalTime;

/**
 * @param name event name
 * @param description description name
 * @param weekday day of week
 * @param category name of category
 * @param startTime event start time
 * @param duration event length
 */
public record EventJson(@JsonProperty("name") String name,
                        @JsonProperty("description") String description,
                        @JsonProperty("days-of-week") Weekday weekday,
                        @JsonProperty("category") String category,
                        @JsonProperty("start-time") LocalTime startTime,
                        @JsonProperty("duration") int[] duration) {
}
