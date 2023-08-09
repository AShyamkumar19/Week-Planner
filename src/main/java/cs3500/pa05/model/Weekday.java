package cs3500.pa05.model;

/**
 * day of the week enum
 */
public enum Weekday {
  SUNDAY(1),
  MONDAY(2),
  TUESDAY(3),
  WEDNESDAY(4),
  THURSDAY(5),
  FRIDAY(6),
  SATURDAY(7);

  private final int value;

  Weekday(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}
