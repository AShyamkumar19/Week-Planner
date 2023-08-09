package cs3500.pa05.model;

import java.util.Objects;

/**
 * abstract class for tasks and events
 */
public abstract class Todo {

  private String name;
  private String description;
  private Weekday weekday;

  private String category;

  /**
   * @param name to do name
   * @param description description for the to do
   * @param weekday day of the week
   * @param category category
   */
  public Todo(String name, String description, Weekday weekday, String category) {
    this.name = name;
    this.description = description;
    this.weekday = weekday;
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Weekday getDayOfWeek() {
    return weekday;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDayOfWeek(Weekday weekday) {
    this.weekday = weekday;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public abstract String toString();

  public abstract boolean isEvent();



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Todo todo = (Todo) o;
    return Objects.equals(name, todo.name)
        &&
        weekday == todo.weekday;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, weekday);
  }

}
