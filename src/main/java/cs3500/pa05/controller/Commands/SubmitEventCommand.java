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
 * represents a command for submitting a to-do event to the planner
 *
 */
public class SubmitEventCommand implements Command {

  private Stage stage;
  private String eventName;
  private Weekday weekday;
  private LocalTime startTime;
  private int[] hoursAndMinutes;
  private String description;
  private String category;
  private AppContext appContext;

  /**
   * @param eventName       the name of the new event
   * @param weekday       the day of the week
   * @param startTime       when the event starts
   * @param hoursAndMinutes how long it is
   * @param description     optional description
   * @param category        optional category
   */
  public SubmitEventCommand(String eventName, Weekday weekday, LocalTime startTime,
                            int[] hoursAndMinutes, String description,
                            String category, AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
    this.eventName = eventName;
    this.weekday = weekday;
    this.startTime = startTime;
    this.hoursAndMinutes =
        hoursAndMinutes.clone();  // clone the array to avoid exposing internal data
    this.description = description;
    this.category = category;

  }

  /**
   * this method is where we put the functionality of each button
   *
   */
  @Override
  public void execute() {
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

      try {
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("weekView.fxml"));
        loader.setController(appContext.getWvc());
        Scene s = loader.load();
        stage.setScene(s);
      } catch (IOException exception) {
        // Handle the IOException here
        Alert ioAlert = new Alert(Alert.AlertType.ERROR);
        ioAlert.setTitle("Loading Error");
        ioAlert.setHeaderText(null);
        ioAlert.setContentText("Failed to load the FXML file: " + exception.getMessage());
        ioAlert.showAndWait();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
