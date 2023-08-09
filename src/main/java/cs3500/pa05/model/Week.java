package cs3500.pa05.model;


import java.util.ArrayList;
import java.util.List;

/**
 * our model
 */
public class Week {
  private String weekName;
  private List<Day> days;
  private int maxEvents;
  private int maxTasks;
  private String quotes;
  private String notes;
  private List<Todo> todoList;

  /**
   * @param weekName name of the week
   * @param days the days in the week
   * @param maxEvents max number of events
   * @param maxTasks ma number of tasks
   */
  public Week(String weekName, List<Day> days, int maxEvents, int maxTasks) {
    this.weekName = weekName;
    this.days = new ArrayList<>();
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = "";
    this.quotes = "";
    this.todoList = new ArrayList<>();
    initDays();
  }

  /**
   *
   * @param weekName
   * @param days
   * @param maxEvents
   * @param maxTasks
   * @param notes
   * @param quotes
   * @param todoList
   */
  public Week(String weekName, List<Day> days, int maxEvents, int maxTasks,
              String notes, String quotes, List<Todo> todoList) {
    this.weekName = weekName;
    this.days = days;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = notes;
    this.quotes = quotes;
    this.todoList = todoList;
  }


  private void initDays() {
    for (Weekday weekday : Weekday.values()) {
      Day d = new Day(weekday, maxEvents, maxTasks);
      this.days.add(d);  // add the new day to the list
    }
  }
  public String getQuotes() {
    return quotes;
  }

  public void setQuotes(String quotes) {
    this.quotes = quotes;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public List<Day> getDays() {
    return this.days;
  }

  public String getWeekName() {
    return this.weekName;
  }

  public int getMaxTasks() {
    return this.maxTasks;
  }

  public int getMaxEvents() {
    return this.maxEvents;
  }

  public List<Todo> getTodoList() {
    return todoList;
  }

  /**
   * @param t add the task
   */
  public void addTask(Task t) {
    todoList.add(t);

    Weekday weekday = t.getDayOfWeek();

    for (Day day : this.days) {
      if (day.getDayOfWeek() == weekday) {
        day.addTask(t);
        break;
      }
    }
  }

  /**
   * @param e adds this event
   */
  public void addEvent(Event e) {
    todoList.add(e);

    Weekday weekday = e.getDayOfWeek();

    for (Day day : this.days) {
      if (day.getDayOfWeek() == weekday) {
        day.addEvent(e);
        break;
      }
    }
  }

  /**
   *
   * @param wd - takes in weekday
   * @return Day
   */
  public Day getDay(Weekday wd) {
    Day d = null;
    for (Day day : this.days) {
      if (day.getDayOfWeek() == wd) {
        d = day;
      }
    }
    return d;
  }

  public void addQuote(String quote) {
    setQuotes(quote);
  }

  public void addNote(String note) {
    setNotes(note);
  }

  /**
   * @param t a todo to delete
   */
  public void deleteTodo(Todo t) {
    todoList.remove(t);

    Weekday weekday = t.getDayOfWeek();
    for (Day day : this.days) {
      if (day.getDayOfWeek() == weekday) {
        day.removeTodo(t);
      }
    }
  }
}

