package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Todo;
import cs3500.pa05.model.Week;
import javafx.stage.Stage;

/**
 * The AppContext class represents the application context, which holds the stage, current week,
 * and week view controller.
 */
public class AppContext {
  private final Stage stage;
  private Week week;
  private WeekviewController wvc;

  /**
   * Constructs an AppContext with the specified stage.
   *
   * @param stage the stage for the application
   */
  public AppContext(Stage stage) {
    this.stage = stage;
  }

  /**
   * Constructs an AppContext with the specified stage and week.
   *
   * @param stage the stage for the application
   * @param week the current week
   */
  public AppContext(Stage stage, Week week) {
    this.stage = stage;
    this.week = week;
  }

  /**
   * Sets the current week in the application context.
   *
   * @param week the current week
   */
  public void setWeek(Week week) {
    this.week = week;
  }

  /**
   * Returns the stage of the application.
   *
   * @return the stage
   */
  public Stage getStage() {
    return stage;
  }

  /**
   * Returns the current week.
   *
   * @return the current week
   */
  public Week getWeek() {
    return week;
  }

  /**
   * Returns the week view controller.
   *
   * @return the week view controller
   */
  public WeekviewController getWvc() {
    return wvc;
  }

  /**
   * Adds an event to the current week.
   *
   * @param e the event to add
   * @throws IllegalArgumentException if the maximum number of events for the day is reached
   */
  public void addEvent(Event e) {
    if (!week.getDay(e.getDayOfWeek()).eventAtMax()) {
      week.addEvent(e);
      wvc.assignTodos();
    } else {
      throw new IllegalArgumentException("Exceeded the maximum number of events for this day.");
    }
  }

  /**
   * Adds a task to the current week.
   *
   * @param t the task to add
   * @throws IllegalArgumentException if the maximum number of tasks for the day is reached
   */
  public void addTask(Task t) {
    if (!week.getDay(t.getDayOfWeek()).taskAtMax()) {
      week.addTask(t);
      wvc.assignTodos();
    } else {
      throw new IllegalArgumentException("Exceeded the maximum number of tasks for this day.");
    }
  }

  /**
   * Sets the week view controller.
   */
  public void setWvc() {
    this.wvc = new WeekviewController(this);
  }

  /**
   * Adds a note to the current week.
   *
   * @param note the note to add
   */
  public void addNote(String note) {
    week.addNote(note);
  }

  /**
   * Adds a quote to the current week.
   *
   * @param quote the quote to add
   */
  public void addQuote(String quote) {
    week.addQuote(quote);
  }

  public void removeTodo(Todo todo) {
    week.deleteTodo(todo);
  }
}
