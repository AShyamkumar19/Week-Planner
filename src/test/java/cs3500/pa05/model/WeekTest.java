package cs3500.pa05.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeekTest {

  @Test
  public void testGetQuotes_Default() {
    Week week = new Week("Week 1", new ArrayList<>(), 5, 10);

    assertEquals("", week.getQuotes());
  }

  @Test
  public void testGetQuotes() {
    List<Day> days = new ArrayList<>();
    Week week = new Week("Week 2", days, 5, 10, "", "This is a quote", new ArrayList<>());

    assertEquals("This is a quote", week.getQuotes());
  }

  @Test
  public void testSetQuotes() {
    Week week = new Week("Week 3", new ArrayList<>(), 5, 10);

    week.setQuotes("New quote");
    assertEquals("New quote", week.getQuotes());
  }

  @Test
  public void testGetNotes_Default() {
    Week week = new Week("Week 4", new ArrayList<>(), 5, 10);

    assertEquals("", week.getNotes());
  }

  @Test
  public void testGetNotes() {
    List<Day> days = new ArrayList<>();
    Week week = new Week("Week 5", days, 5, 10, "These are notes", "", new ArrayList<>());

    assertEquals("These are notes", week.getNotes());
  }

  @Test
  public void testSetNotes() {
    Week week = new Week("Week 6", new ArrayList<>(), 5, 10);

    week.setNotes("New notes");
    assertEquals("New notes", week.getNotes());
  }

  @Test
  public void testGetDays() {
    List<Day> days = new ArrayList<>();
    Week week = new Week("Week 7", days, 5, 10);

    assertEquals(7, week.getDays().size());
  }

  @Test
  public void testGetWeekName() {
    Week week = new Week("Week 8", new ArrayList<>(), 5, 10);

    assertEquals("Week 8", week.getWeekName());
  }

  @Test
  public void testGetMaxTasks() {
    Week week = new Week("Week 9", new ArrayList<>(), 5, 10);

    assertEquals(10, week.getMaxTasks());
  }

  @Test
  public void testGetMaxEvents() {
    Week week = new Week("Week 10", new ArrayList<>(), 5, 10);

    assertEquals(5, week.getMaxEvents());
  }

  @Test
  public void testGetTodoList() {
    List<Todo> todoList = new ArrayList<>();
    Week week = new Week("Week 11", new ArrayList<>(), 5, 10, "", "", todoList);

    assertEquals(todoList, week.getTodoList());
  }

  @Test
  public void testAddTask() {
    Task task = new Task("Study", "Prepare for exam", Weekday.MONDAY, "Academic");
    Week week = new Week("Week 12", new ArrayList<>(), 5, 10);

    week.addTask(task);
    assertTrue(week.getTodoList().contains(task));

    Day monday = week.getDay(Weekday.MONDAY);
    assertNotNull(monday);
    assertTrue(monday.getTasks().contains(task));
  }

  @Test
  public void testAddEvent() {
    Event event = new Event("Meeting", "Team meeting", Weekday.TUESDAY, "Work", null, new int[]{1, 30});
    Week week = new Week("Week 13", new ArrayList<>(), 5, 10);

    week.addEvent(event);
    assertTrue(week.getTodoList().contains(event));

    Day tuesday = week.getDay(Weekday.TUESDAY);
    assertNotNull(tuesday);
    assertTrue(tuesday.getEvents().contains(event));
  }

  @Test
  public void testGetDay() {
    List<Day> days = new ArrayList<>();
    Day day = new Day(Weekday.WEDNESDAY, 5, 10);
    days.add(day);
    Week week = new Week("Week 14", days, 5, 10);

    assertEquals(day.getDayOfWeek(), week.getDay(Weekday.WEDNESDAY).getDayOfWeek());
  }

  @Test
  public void testDeleteTodo() {
    Task task = new Task("Task", "Do something", Weekday.THURSDAY, "Personal");
    Week week = new Week("Week 15", new ArrayList<>(), 5, 10);
    week.addTask(task);

    week.deleteTodo(task);
    assertFalse(week.getTodoList().contains(task));

    Day thursday = week.getDay(Weekday.THURSDAY);
    assertNotNull(thursday);
    assertFalse(thursday.getTasks().contains(task));
  }
}
