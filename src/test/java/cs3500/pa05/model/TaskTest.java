package cs3500.pa05.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

  @Test
  public void testGetIsComplete_Default() {
    Task task = new Task("Study", "Prepare for exam", Weekday.MONDAY, "Academic");

    assertFalse(task.getIsComplete());
  }

  @Test
  public void testGetIsComplete() {
    Task task = new Task("Read", "Read a book", Weekday.TUESDAY, "Personal", true);

    assertTrue(task.getIsComplete());
  }

  @Test
  public void testSetComplete() {
    Task task = new Task("Write", "Write a report", Weekday.WEDNESDAY, "Work");

    task.setComplete(true);
    assertTrue(task.getIsComplete());

    task.setComplete(false);
    assertFalse(task.getIsComplete());
  }

  @Test
  public void testIsEvent() {
    Task task = new Task("Clean", "Clean the house", Weekday.THURSDAY, "Household");

    assertFalse(task.isEvent());
  }

  @Test
  public void testToString() {
    Task task = new Task("Buy groceries", "Buy milk and bread", Weekday.FRIDAY, "Shopping");

    String expectedString = "Shopping: Buy groceries Buy milk and bread";
    assertEquals(expectedString, task.toString());
  }
}
