package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cs3500.pa05.model.Jsons.DayJson;
import cs3500.pa05.model.Jsons.EventJson;
import cs3500.pa05.model.Jsons.TaskJson;
import cs3500.pa05.model.Jsons.WeekJson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple utils class used to hold static methods that help with serializing and deserializing JSON.
 */
public class JsonUtils {
  /**
   * Converts a given record object to a JsonNode.
   * @param week the record to convert
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public void saveFile(Week week, String path) throws IllegalArgumentException {
    try {
      if (!path.endsWith(".bujo")) {
        String bujo = ".bujo";
        StringBuilder sb = new StringBuilder();
        path = sb.append(path).append("\\").append(week.getWeekName()).append(bujo).toString();
      }
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());

      List<DayJson> dayJson = new ArrayList<>();


      for (Day day : week.getDays()) {
        List<EventJson> events = day.getEvents().stream()
            .map(event -> new EventJson(event.getName(), event.getDescription(), event.getDayOfWeek(),
                event.getCategory(), event.getStartTime(), event.getHoursAndMinutes())).collect(Collectors.toList());


        List<TaskJson> tasks = day.getTasks().stream()
            .map(event -> new TaskJson(event.getName(), event.getDescription(), event.getDayOfWeek(),
                event.getCategory(), event.isCompleteStr())).collect(Collectors.toList());

        dayJson.add(new DayJson(day.getDayOfWeek(), events, tasks, day.getMaxEvents(), day.getMaxTasks()));
      }
      List<EventJson> weekEvents = new ArrayList<>();
      List<TaskJson> weekTasks = new ArrayList<>();

      for (Todo todo : week.getTodoList()) {
        if (todo instanceof Task) {
          Task t = (Task) todo;
          TaskJson taskJson = new TaskJson(t.getName(), t.getDescription(), t.getDayOfWeek(),
              t.getCategory(), t.isCompleteStr());
          weekTasks.add(taskJson);
        } else {
          Event eventBad = (Event) todo;
          EventJson eventGood = new EventJson(eventBad.getName(), eventBad.getDescription(), eventBad.getDayOfWeek(),
              eventBad.getCategory(), eventBad.getStartTime(), eventBad.getHoursAndMinutes());
          weekEvents.add(eventGood);
        }
      }


      WeekJson weekJson = new WeekJson(week.getWeekName(), dayJson, week.getMaxEvents(), week.getMaxTasks(),
          week.getNotes(), week.getQuotes(), weekEvents, weekTasks);
      String wj = mapper.writeValueAsString(weekJson);
      Files.write(Paths.get(path), wj.getBytes(StandardCharsets.UTF_8));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  /**
   * @param json
   * @return Week
   */
  public Week openFile(String json) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());

      WeekJson weekJson = mapper.readValue(json, WeekJson.class);
      List<Day> days = new ArrayList<>();
      List<Todo> weekTodos = new ArrayList<>();

      for (DayJson dayJson : weekJson.days()) {
        List<Todo> dayTodos = new ArrayList<>();

        List<Todo> tasks = dayJson.tasks().stream()
            .map(taskJson -> new Task(taskJson.name(), taskJson.description(),
                taskJson.weekday(), taskJson.category(), taskJson.isCompleted()))
            .collect(Collectors.toList());

        List<Todo> events = dayJson.events().stream()
            .map(eventJson -> new Event(eventJson.name(), eventJson.description(),
                eventJson.weekday(), eventJson.category(), eventJson.startTime(), eventJson.duration()))
            .collect(Collectors.toList());

        dayTodos.addAll(tasks);
        dayTodos.addAll(events);

        weekTodos.addAll(tasks);
        weekTodos.addAll(events);

        days.add(new Day(dayJson.weekday(), dayTodos, dayJson.maxEvents(), dayJson.maxTasks()));
      }

      // NEED A LOCAL VARIABLE CALLED ALLWEEKTODOS THAT COMBINES THE LIST<TASKS> AND LIST<EVENT>


      return new Week(weekJson.weekName(), days, weekJson.maxEvents(), weekJson.maxTasks(),
          weekJson.notes(), weekJson.quotes(), weekTodos);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String formatDuration(Duration duration) {
    long totalMinutes = duration.toMinutes();
    long hours = totalMinutes / 60;
    long minutes = totalMinutes % 60;
    return String.format("%02d:%02d", hours, minutes);
  }
}