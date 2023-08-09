package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Weekday;
import java.io.IOException;
import java.time.LocalTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 */
public class SaveEventChangesCommand implements Command {
  private Stage stage;
  private String eventName;
  private Weekday weekday;
  private LocalTime startTime;
  private int[] hoursAndMinutes;
  private String description;
  private String category;
  private AppContext appContext;
  private Event event;

  /**
   *
   * @param eventName name of event
   * @param weekday day of week
   * @param startTime event start time
   * @param hoursAndMinutes event length
   * @param description event description
   * @param category event category
   * @param appContext app context
   * @param event the event before changes
   */
  public SaveEventChangesCommand(String eventName, Weekday weekday, LocalTime startTime,
                                 int[] hoursAndMinutes, String description, String category,
                                 AppContext appContext,
                                 Event event) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
    this.eventName = eventName;
    this.weekday = weekday;
    this.startTime = startTime;
    this.hoursAndMinutes =
        hoursAndMinutes.clone();  // clone the array to avoid exposing internal data
    this.description = description;
    this.category = category;
    this.event = event;
  }


  /**
   * Executes the command.
   */
  @Override
  public void execute() {
    createAndReplace();
  }

  private void createAndReplace() {
    appContext.removeTodo(event);
    try {
      Event e = new Event(eventName, description, weekday, category, startTime, hoursAndMinutes);
      appContext.addEvent(e);
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("weekView.fxml"));
      loader.setController(appContext.getWvc());
      Scene s = loader.load();
      stage.setScene(s);
    } catch (IllegalArgumentException e) {
      // Create an alert dialog to show the error
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Exceeds the Max Number of Events for this Day");
      alert.setHeaderText(null);
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
