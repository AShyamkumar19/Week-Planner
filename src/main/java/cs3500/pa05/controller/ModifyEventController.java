package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.SaveEventChangesCommand;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Weekday;
import java.time.Duration;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for editing events
 */
public class ModifyEventController implements Controller {

  private Stage stage;

  @FXML
  private ComboBox<String> dayComboBox;
  @FXML
  private ComboBox<String> timeComboBox;
  @FXML
  private TextField eventNameField;
  @FXML
  private TextField categoryNameField;
  @FXML
  private TextField descriptionField;
  @FXML
  private ComboBox<String> durationComboBox;
  @FXML
  private Button saveChanges;
  @FXML
  private Button deleteButton;

  @FXML
  private CheckBox checkBox;

  private AppContext appContext;

  private Event event;

  /**
   *
   * @param appContext - the controller
   * @param event - current event
   */
  public ModifyEventController(AppContext appContext, Event event) {
    this.stage = appContext.getStage();
    this.appContext = appContext;
    this.event = event;
  }

  @Override
  public void initialize() {

    dayComboBox.getItems().addAll("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday");

    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 60; j += 15) {
        timeComboBox.getItems().add(String.format("%02d:%02d", i, j));
        durationComboBox.getItems().add(String.format("%02d:%02d", i, j));
      }
    }

    dayComboBox.setValue(event.getDayOfWeek().toString());
    timeComboBox.setValue(event.getStartTime().toString());
    durationComboBox.setValue(formatDuration(event.getDuration()));
    eventNameField.setText(event.getName());
    categoryNameField.setText(event.getCategory());
    descriptionField.setText(event.getDescription());

    this.saveChanges.setOnMouseClicked(event -> {
      String eventName = eventNameField.getText();
      String weekdayStr = dayComboBox.getValue();
      String startTimeStr = timeComboBox.getValue();
      String durationStr = durationComboBox.getValue();
      String description = descriptionField.getText();
      String category = categoryNameField.getText();

      // Check if the required fields are filled
      if (eventName == null || weekdayStr == null || startTimeStr == null || durationStr == null) {
        // Alert the user that they need to fill out all fields
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Input Error");
        alert.setContentText("Please fill out all fields before submitting.");
        alert.showAndWait();
        return;
      }

      Weekday weekday = Weekday.valueOf(weekdayStr.toUpperCase());
      LocalTime startTime = LocalTime.parse(startTimeStr);
      int[] hoursAndMinutes = parseDuration(durationStr);

      // Create and execute the command
      SaveEventChangesCommand command =
          new SaveEventChangesCommand(eventName, weekday, startTime, hoursAndMinutes,
              description, category, appContext, this.event);
      command.execute();
    });
  }

  /**
   * Parses the duration string into hours and minutes.
   *
   * @param durationStr the duration string in format "HH:mm"
   * @return an array containing hours and minutes
   */
  private int[] parseDuration(String durationStr) {
    String[] parts = durationStr.split(":");
    int hours = Integer.parseInt(parts[0]);
    int minutes = Integer.parseInt(parts[1]);
    return new int[]{hours, minutes};
  }

  /**
   * Formats the duration from Duration object to String format "HH:mm".
   *
   * @param duration the duration as a Duration object
   * @return a string representing the duration in "HH:mm" format
   */
  private String formatDuration(Duration duration) {
    long totalMinutes = duration.toMinutes();
    long hours = totalMinutes / 60;
    long minutes = totalMinutes % 60;
    return String.format("%02d:%02d", hours, minutes);
  }

}
