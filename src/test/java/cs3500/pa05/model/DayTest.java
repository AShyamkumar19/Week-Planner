package cs3500.pa05.model;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DayTest {

  private Day day;
  private Task task1;
  private Task task2;
  private Event event1;
  private Event event2;

  @BeforeEach
  public void setUp() {
    day = new Day(Weekday.MONDAY, 2, 3);
    task1 = new Task("Task 1", "Description 1", Weekday.MONDAY, "Category 1");
    task2 = new Task("Task 2", "Description 2", Weekday.MONDAY, "Category 2");
    event1 = new Event("Event 1", "Description 1", Weekday.MONDAY, "Category 1",
        LocalTime.of(9, 0), new int[]{1, 30});
    event2 = new Event("Event 2", "Description 2", Weekday.MONDAY, "Category 2",
        LocalTime.of(13, 0), new int[]{2, 0});
  }

  @Test
  public void testAddEvent() {
    day.addEvent(event1);
    List<Event> events = day.getEvents();
    assertEquals(1, events.size());
    assertTrue(events.contains(event1));
  }

  @Test
  public void testAddTask() {
    day.addTask(task1);
    List<Task> tasks = day.getTasks();
    assertEquals(1, tasks.size());
    assertTrue(tasks.contains(task1));
  }

  @Test
  public void testRemoveTodo() {
    day.addEvent(event1);
    day.addTask(task1);
    day.removeTodo(event1);
    List<Event> events = day.getEvents();
    assertEquals(0, events.size());
    assertFalse(events.contains(event1));

    List<Task> tasks = day.getTasks();
    assertEquals(1, tasks.size());
    assertTrue(tasks.contains(task1));
  }

  @Test
  public void testGetDayOfWeek() {
    Weekday weekday = day.getDayOfWeek();
    assertEquals(Weekday.MONDAY, weekday);
  }

  @Test
  public void testGetMaxEvents() {
    int maxEvents = day.getMaxEvents();
    assertEquals(2, maxEvents);
  }

  @Test
  public void testGetMaxTasks() {
    int maxTasks = day.getMaxTasks();
    assertEquals(3, maxTasks);
  }

  @Test
  public void testGetTasks() {
    day.addTask(task1);
    day.addTask(task2);
    List<Task> tasks = day.getTasks();
    assertEquals(2, tasks.size());
    assertTrue(tasks.contains(task1));
    assertTrue(tasks.contains(task2));
  }

  @Test
  public void testGetEvents() {
    day.addEvent(event1);
    day.addEvent(event2);
    List<Event> events = day.getEvents();
    assertEquals(2, events.size());
    assertTrue(events.contains(event1));
    assertTrue(events.contains(event2));
  }

  @Test
  public void testEventAtMax() {
    day.addEvent(event1);
    boolean isEventAtMax = day.eventAtMax();
    assertFalse(isEventAtMax);

    day.addEvent(event2);
    isEventAtMax = day.eventAtMax();
    assertTrue(isEventAtMax);
  }

  @Test
  public void testTaskAtMax() {
    day.addTask(task1);
    boolean isTaskAtMax = day.taskAtMax();
    assertFalse(isTaskAtMax);

    day.addTask(task2);
    isTaskAtMax = day.taskAtMax();
    assertFalse(isTaskAtMax);

    Task task3 = new Task("Task 3", "Description 3", Weekday.MONDAY, "Category 3");
    day.addTask(task3);
    isTaskAtMax = day.taskAtMax();
    assertTrue(isTaskAtMax);
  }
}
