package cs3500.pa05.model.Jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Weekday;

/**
 * @param name name of task
 * @param description task description
 * @param weekday weekday of task
 * @param category task category
 * @param isCompletedStr task completion status
 */
public record TaskJson(@JsonProperty("name") String name,
                       @JsonProperty("description") String description,
                       @JsonProperty("day-of-week") Weekday weekday,
                       @JsonProperty("category") String category,
                       @JsonProperty("completed") String isCompletedStr) {

  public boolean isCompleted() {
    return Boolean.parseBoolean(isCompletedStr);
  }
}


