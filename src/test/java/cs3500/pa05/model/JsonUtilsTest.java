package cs3500.pa05.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonUtilsTest {

  @Test
  public void testSaveFile() throws IOException {
    // Create a sample Week object
    List<Day> days = new ArrayList<>();
    Day day1 = new Day(Weekday.MONDAY, 5, 10);
    Day day2 = new Day(Weekday.TUESDAY, 5, 10);
    days.add(day1);
    days.add(day2);

    Task task1 = new Task("Task 1", "Description 1", Weekday.MONDAY, "Category 1", true);
    Task task2 = new Task("Task 2", "Description 2", Weekday.TUESDAY, "Category 2", false);
    Event event1 =
        new Event("Event 1", "Description 1", Weekday.MONDAY, "Category 1", LocalTime.of(9, 0),
            new int[] {1, 30});
    Event event2 =
        new Event("Event 2", "Description 2", Weekday.TUESDAY, "Category 2", LocalTime.of(14, 30),
            new int[] {2, 0});

    day1.addTask(task1);
    day2.addTask(task2);
    day1.addEvent(event1);
    day2.addEvent(event2);

    List<Todo> todoList = new ArrayList<>();
    todoList.add(task1);
    todoList.add(task2);
    todoList.add(event1);
    todoList.add(event2);

    Week week = new Week("Week 1", days, 5, 10, "", "", todoList);

    // Specify the path to save the file
    String filePath = "test_week.bujo";

    // Save the Week object to a file using JsonUtils
    JsonUtils jsonUtils = new JsonUtils();
    jsonUtils.saveFile(week, filePath);

    // Read the saved file and verify its content
    String jsonContent = null;
    try {
      jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(
        "{\"name\":\"Week 1\",\"days\":[{\"days-of-week\":\"MONDAY\",\"events\":[{\"name\":"
            + "\"Event 1\",\"description\":\"Description 1\",\"days-of-week\":\"MONDAY\",\"category\":\""
            + "Category 1\",\"start-time\":[9,0],\"duration\":[1,30]}],\"tasks\":[{\"name\":\"Task 1\","
            + "\"description\":\"Description 1\",\"day-of-week\":\"MONDAY\",\"category\":\"Category 1\","
            + "\"completed\":\"true\"}],\"max-event\":5,\"max-tasks\":10},{\"days-of-week\":\"TUESDAY\","
            + "\"events\":[{\"name\":\"Event 2\",\"description\":\"Description 2\",\"days-of-week\":\"TUESDAY\","
            + "\"category\":\"Category 2\",\"start-time\":[14,30],\"duration\":[2,0]}],\"tasks\":[{\"name\":\"Task 2\","
            + "\"description\":\"Description 2\",\"day-of-week\":\"TUESDAY\",\"category\":\"Category 2\","
            + "\"completed\":\"false\"}],\"max-event\":5,\"max-tasks\":10}],\"max-events\":5,\"max-tasks\":10,"
            + "\"notes\":\"\",\"quotes\":\"\",\"events\":[{\"name\":\"Event 1\",\"description\":\"Description 1\","
            + "\"days-of-week\":\"MONDAY\",\"category\":\"Category 1\",\"start-time\":[9,0],\"duration\":[1,30]},"
            + "{\"name\":\"Event 2\",\"description\":\"Description 2\",\"days-of-week\":\"TUESDAY\",\"category\":"
            + "\"Category 2\",\"start-time\":[14,30],\"duration\":[2,0]}],\"tasks\":[{\"name\":\"Task 1\",\"description"
            + "\":\"Description 1\",\"day-of-week\":\"MONDAY\",\"category\":\"Category 1\",\"completed\":\"true\"},"
            + "{\"name\":\"Task 2\",\"description\":\"Description 2\",\"day-of-week\":\"TUESDAY\",\"category\":"
            + "\"Category 2\",\"completed\":\"false\"}]}",
        jsonContent);

    // Delete the saved file
    File file = new File(filePath);
    file.delete();
  }

  @Test
  public void testOpenFile() {
    // Create a sample JSON string representing a Week object
    String json =
        "{\"name\":\"Week 1\",\"days\":[{\"days-of-week\":\"MONDAY\",\"events\":[{\"name\":"
            + "\"Event 1\",\"description\":\"Description 1\",\"days-of-week\":\"MONDAY\",\"category\":\""
            + "Category 1\",\"start-time\":[9,0],\"duration\":[1,30]}],\"tasks\":[{\"name\":\"Task 1\","
            + "\"description\":\"Description 1\",\"day-of-week\":\"MONDAY\",\"category\":\"Category 1\","
            + "\"completed\":\"true\"}],\"max-event\":5,\"max-tasks\":10},{\"days-of-week\":\"TUESDAY\","
            + "\"events\":[{\"name\":\"Event 2\",\"description\":\"Description 2\",\"days-of-week\":\"TUESDAY\","
            + "\"category\":\"Category 2\",\"start-time\":[14,30],\"duration\":[2,0]}],\"tasks\":[{\"name\":\"Task 2\","
            + "\"description\":\"Description 2\",\"day-of-week\":\"TUESDAY\",\"category\":\"Category 2\","
            + "\"completed\":\"false\"}],\"max-event\":5,\"max-tasks\":10}],\"max-events\":5,\"max-tasks\":10,"
            + "\"notes\":\"\",\"quotes\":\"\",\"events\":[{\"name\":\"Event 1\",\"description\":\"Description 1\","
            + "\"days-of-week\":\"MONDAY\",\"category\":\"Category 1\",\"start-time\":[9,0],\"duration\":[1,30]},"
            + "{\"name\":\"Event 2\",\"description\":\"Description 2\",\"days-of-week\":\"TUESDAY\",\"category\":"
            + "\"Category 2\",\"start-time\":[14,30],\"duration\":[2,0]}],\"tasks\":[{\"name\":\"Task 1\",\"description"
            + "\":\"Description 1\",\"day-of-week\":\"MONDAY\",\"category\":\"Category 1\",\"completed\":\"true\"},"
            + "{\"name\":\"Task 2\",\"description\":\"Description 2\",\"day-of-week\":\"TUESDAY\",\"category\":"
            + "\"Category 2\",\"completed\":\"false\"}]}";
    // Deserialize the JSON string to a Week object using JsonUtils
    JsonUtils jsonUtils = new JsonUtils();
    Week week = jsonUtils.openFile(json);
    assertEquals(week.getWeekName(), "Week 1");
  }
}

    // Verify the contents of the
