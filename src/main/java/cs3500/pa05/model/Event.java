package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.Duration;
import java.time.LocalTime;

/**
 * class to represent an event
 */
public class Event extends Todo {

  private LocalTime startTime;
  private Duration duration;
  private int[] hoursAndMinutes;

  /**
   *
   * @param name event name
   * @param description event description
   * @param weekday even day
   * @param category event category
   * @param startTime event start time
   * @param hoursAndMinutes even length
   */
  public Event(String name, String description, Weekday weekday, String category, LocalTime startTime, int[] hoursAndMinutes) {
    super(name, description, weekday, category);
    this.startTime = startTime;
    this.hoursAndMinutes = hoursAndMinutes;
    this.duration = Duration.ofHours(hoursAndMinutes[0]).plusMinutes(hoursAndMinutes[1]);
  }

  /**
   * @param json takes in json
   * @return creates event
   */
  @JsonCreator
  public static Event createFromJson(cs3500.pa05.model.Jsons.EventJson json) {
    return new Event(
        json.name(),
        json.description(),
        json.weekday(),
        json.category(),
        json.startTime(),
        json.duration()
    );
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public Duration getDuration() {
    duration = Duration.ofHours(hoursAndMinutes[0]).plusMinutes(hoursAndMinutes[1]);
    return duration;
  }

  public int[] getHoursAndMinutes() {
    return hoursAndMinutes;
  }

  @Override
  public String toString() {
    return getCategory() + ": " + getName() + " "
        + startTime.toString() + " to: "
        + startTime.plus(duration).toString() + " " + getDescription();
  }

  @Override
  public boolean isEvent() {
    return true;
  }
}
