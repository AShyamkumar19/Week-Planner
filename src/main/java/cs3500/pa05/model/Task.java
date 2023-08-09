package cs3500.pa05.model;

/**
 * Represents a task.
 */
public class Task extends Todo {
  private boolean complete;

  /**
   * Constructs a Task object with the specified name, description, weekday, and category.
   * The task is initially marked as incomplete.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param weekday     the weekday associated with the task
   * @param category    the category of the task
   */
  public Task(String name, String description, Weekday weekday, String category) {
    super(name, description, weekday, category);
    this.complete = false;  // new tasks are not complete by default
  }

  /**
   * Constructs a Task object with the specified name, description, weekday, category, and completion status.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param weekday     the weekday associated with the task
   * @param category    the category of the task
   * @param complete    the completion status of the task
   */
  public Task(String name, String description, Weekday weekday, String category, boolean complete) {
    super(name, description, weekday, category);
    this.complete = complete;
  }

  /**
   * Returns a string representation of the completion status of the task.
   *
   * @return a string representation of the completion status
   */
  public String isCompleteStr() {
    return Boolean.toString(this.complete);
  }

  /**
   * Returns the completion status of the task.
   *
   * @return true if the task is complete, false otherwise
   */
  public boolean getIsComplete() {
    return this.complete;
  }

  /**
   * Sets the completion status of the task.
   *
   * @param complete the completion status to set
   */
  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  /**
   * Returns whether the task is considered an event.
   *
   * @return false since a task is not an event
   */
  @Override
  public boolean isEvent() {
    return false;
  }

  /**
   * Returns a string representation of the task, including its category, name, and description.
   *
   * @return a string representation of the task
   */
  @Override
  public String toString() {
    return getCategory() + ": " + getName()+ " " + getDescription();
  }
}
