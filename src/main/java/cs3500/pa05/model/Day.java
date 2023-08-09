package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day.
 */
public class Day {

  private final Weekday dayOfWeek;
  private List<Todo> todoList;
  private int maxEvents;
  private int maxTasks;
  private int taskCount = 0;
  private int eventCount = 0;

  /**
   * Constructs a Day object with the specified day of the week, maximum events, and maximum tasks.
   *
   * @param dayOfWeek  the day of the week associated with this day
   * @param maxEvents  the maximum number of events allowed in this day
   * @param maxTasks   the maximum number of tasks allowed in this day
   */
  public Day(Weekday dayOfWeek, int maxEvents, int maxTasks) {
    this.dayOfWeek = dayOfWeek;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.todoList = new ArrayList<>();
  }

  /**
   * Constructs a Day object with the specified day of the week, list of todos,
   * maximum events, and maximum tasks.
   *
   * @param dayOfWeek  the day of the week associated with this day
   * @param todos      the list of todos for this day
   * @param maxEvents  the maximum number of events allowed in this day
   * @param maxTasks   the maximum number of tasks allowed in this day
   */
  public Day(Weekday dayOfWeek, List<Todo> todos, int maxEvents, int maxTasks) {
    this.dayOfWeek = dayOfWeek;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.todoList = todos;
  }

  /**
   * Adds an event to this day's todo list.
   *
   * @param e  the event to be added
   */
  public void addEvent(Event e) {
    if (eventCount < maxEvents) {
      todoList.add(e);
      eventCount++;
    } else {
      // todo: throw exception?
      return;
    }
  }

  /**
   * Adds a task to this day's todo list.
   *
   * @param t  the task to be added
   */
  public void addTask(Task t) {
    if (taskCount < maxTasks) {
      todoList.add(t);
      taskCount++;
    } else {
      return;
    }
  }

  /**
   * Removes a todo from this day's todo list.
   *
   * @param todo  the todo to be removed
   */
  public void removeTodo(Todo todo) {
    todoList.remove(todo);

    if (todo instanceof Task) {
      taskCount--;
    } else {
      eventCount--;
    }
  }

  /**
   * Returns the day of the week associated with this day.
   *
   * @return the day of the week
   */
  public Weekday getDayOfWeek() {
    return this.dayOfWeek;
  }

  /**
   * Returns the maximum number of events allowed in this day.
   *
   * @return the maximum number of events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Returns the maximum number of tasks allowed in this day.
   *
   * @return the maximum number of tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Returns a list of tasks in this day's todo list.
   *
   * @return the list of tasks
   */
  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();
    for (Todo todo : todoList) {
      if (todo instanceof Task) {
        tasks.add((Task) todo);
      }
    }
    return tasks;
  }

  /**
   * Returns a list of events in this day's todo list.
   *
   * @return the list of events
   */
  public List<Event> getEvents() {
    List<Event> events = new ArrayList<>();
    for (Todo todo : todoList) {
      if (todo instanceof Event) {
        events.add((Event) todo);
      }
    }
    return events;
  }

  /**
   * Checks if the number of events has reached the maximum allowed.
   *
   * @return true if the number of events is at the maximum, false otherwise
   */
  public boolean eventAtMax() {
    return this.getEvents().size() == this.getMaxEvents();
  }

  /**
   * Checks if the number of tasks has reached the maximum allowed.
   *
   * @return true if the number of tasks is at the maximum, false otherwise
   */
  public boolean taskAtMax() {
    return this.getTasks().size() == this.getMaxTasks();
  }
}
