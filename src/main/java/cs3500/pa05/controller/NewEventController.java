package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.SubmitEventCommand;
import cs3500.pa05.model.Weekday;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for creating new events.
 */
public class NewEventController implements Controller {

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
  private Button submit;
  @FXML
  private ComboBox<String> durationComboBox;

  private AppContext appContext;

  /**
   * Constructs a NewEventController.
   *
   * @param appContext the application context
   */
  public NewEventController(AppContext appContext) {
    this.stage = appContext.getStage();
    this.appContext = appContext;
  }

  /**
   * Initializes the NewEventController.
   */
  @FXML
  public void initialize() {
    dayComboBox.getItems().addAll("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday");

    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 60; j += 15) {
        timeComboBox.getItems().add(String.format("%02d:%02d", i, j));
      }
    }

    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 60; j += 15) {
        durationComboBox.getItems().add(String.format("%02d:%02d", i, j));
      }
    }

    this.submit.setOnMouseClicked(event -> {
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
      SubmitEventCommand command =
          new SubmitEventCommand(eventName, weekday, startTime, hoursAndMinutes,
              description, category, appContext);
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
}
