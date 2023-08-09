package cs3500.pa05.model;

import java.time.Duration;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

  @Test
  public void testGetStartTime() {
    LocalTime startTime = LocalTime.of(9, 0);
    Event event = new Event("Meeting", "Team meeting", Weekday.MONDAY, "Work", startTime, new int[]{1, 30});

    assertEquals(startTime, event.getStartTime());
  }

  @Test
  public void testGetDuration() {
    LocalTime startTime = LocalTime.of(14, 0);
    Event event = new Event("Presentation", "Project presentation", Weekday.WEDNESDAY, "Academic", startTime, new int[]{2, 0});

    Duration expectedDuration = Duration.ofHours(2).plusMinutes(0);
    assertEquals(expectedDuration, event.getDuration());
  }

  @Test
  public void testGetHoursAndMinutes() {
    int[] hoursAndMinutes = {1, 45};
    Event event = new Event("Meeting", "Team meeting", Weekday.FRIDAY, "Work", LocalTime.of(15, 30), hoursAndMinutes);

    assertArrayEquals(hoursAndMinutes, event.getHoursAndMinutes());
  }

  @Test
  public void testToString() {
    LocalTime startTime = LocalTime.of(10, 30);
    Event event = new Event("Lunch", "Lunch break", Weekday.TUESDAY, "Personal", startTime, new int[]{1, 0});

    String expectedString = "Personal: Lunch 10:30 to: 11:30 Lunch break";
    assertEquals(expectedString, event.toString());
  }

  @Test
  public void testIsEvent() {
    Event event = new Event("Meeting", "Team meeting", Weekday.THURSDAY, "Work", LocalTime.of(13, 0), new int[]{1, 30});

    assertTrue(event.isEvent());
  }
  @Test
  public void testGetName() {
    Event event = new Event("Party", "Birthday party", Weekday.FRIDAY, "Personal", LocalTime.of(18, 0), new int[]{3, 0});
    assertEquals("Party", event.getName());
  }

  @Test
  public void testGetDescription() {
    Event event = new Event("Conference", "Tech conference", Weekday.TUESDAY, "Professional", LocalTime.of(9, 0), new int[]{8, 0});
    assertEquals("Tech conference", event.getDescription());
  }

  @Test
  public void testGetDayOfWeek() {
    Event event = new Event("Concert", "Music concert", Weekday.SATURDAY, "Entertainment", LocalTime.of(20, 0), new int[]{2, 30});
    assertEquals(Weekday.SATURDAY, event.getDayOfWeek());
  }

  @Test
  public void testGetCategory() {
    Event event = new Event("Meeting", "Team meeting", Weekday.MONDAY, "Work", LocalTime.of(10, 0), new int[]{1, 0});
    assertEquals("Work", event.getCategory());
  }

  @Test
  public void testSetName() {
    Event event = new Event("Workout", "Gym session", Weekday.WEDNESDAY, "Health", LocalTime.of(8, 0), new int[]{1, 0});
    event.setName("Exercise");
    assertEquals("Exercise", event.getName());
  }

  @Test
  public void testSetDescription() {
    Event event = new Event("Brunch", "Brunch with friends", Weekday.SUNDAY, "Social", LocalTime.of(11, 0), new int[]{2, 0});
    event.setDescription("Brunch with family");
    assertEquals("Brunch with family", event.getDescription());
  }

  @Test
  public void testSetDayOfWeek() {
    Event event = new Event("Yoga", "Yoga class", Weekday.THURSDAY, "Health", LocalTime.of(7, 0), new int[]{1, 30});
    event.setDayOfWeek(Weekday.FRIDAY);
    assertEquals(Weekday.FRIDAY, event.getDayOfWeek());
  }

  @Test
  public void testSetCategory() {
    Event event = new Event("Seminar", "Business seminar", Weekday.TUESDAY, "Professional", LocalTime.of(10, 0), new int[]{3, 0});
    event.setCategory("Education");
    assertEquals("Education", event.getCategory());
  }

  @Test
  public void testEquals() {
    Event event1 = new Event("Cinema", "Movie with friends", Weekday.SATURDAY, "Entertainment", LocalTime.of(19, 30), new int[]{2, 0});
    Event event2 = new Event("Cinema", "Movie with friends", Weekday.SATURDAY, "Entertainment", LocalTime.of(19, 30), new int[]{2, 0});
    assertTrue(event1.equals(event2));
  }

  @Test
  public void testHashCode() {
    Event event1 = new Event("Workout", "Gym session", Weekday.WEDNESDAY, "Health", LocalTime.of(8, 0), new int[]{1, 0});
    Event event2 = new Event("Workout", "Gym session", Weekday.WEDNESDAY, "Health", LocalTime.of(8, 0), new int[]{1, 0});
    assertEquals(event1.hashCode(), event2.hashCode());
  }

  @Test
  public void testEqualsSameObject() {
    Event event = new Event("Meeting", "Team meeting", Weekday.MONDAY, "Work", LocalTime.of(10, 0), new int[]{1, 0});
    assertTrue(event.equals(event));
  }

  @Test
  public void testEqualsNull() {
    Event event = new Event("Workout", "Gym session", Weekday.WEDNESDAY, "Health", LocalTime.of(8, 0), new int[]{1, 0});
    assertFalse(event.equals(null));
  }

  @Test
  public void testEqualsDifferentClass() {
    Event event = new Event("Brunch", "Brunch with friends", Weekday.SUNDAY, "Social", LocalTime.of(11, 0), new int[]{2, 0});
    String notEvent = "I am not an event";
    assertFalse(event.equals(notEvent));
  }

  @Test
  public void testEqualsDifferentNameAndWeekday() {
    Event event1 = new Event("Cinema", "Movie with friends", Weekday.SATURDAY, "Entertainment", LocalTime.of(19, 30), new int[]{2, 0});
    Event event2 = new Event("Yoga", "Yoga class", Weekday.FRIDAY, "Health", LocalTime.of(7, 0), new int[]{1, 30});
    assertFalse(event1.equals(event2));
  }

  @Test
  public void testEqualsSameNameAndWeekday() {
    Event event1 = new Event("Meeting", "Team meeting", Weekday.MONDAY, "Work", LocalTime.of(10, 0), new int[]{1, 0});
    Event event2 = new Event("Meeting", "Team meeting", Weekday.MONDAY, "Work", LocalTime.of(14, 0), new int[]{1, 0}); // Different start times but same name and weekday
    assertTrue(event1.equals(event2));
  }

}
