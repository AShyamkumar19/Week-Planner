package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.SubmitTaskCommand;
import cs3500.pa05.model.Weekday;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for creating new tasks.
 */
public class NewTaskController {

  @FXML
  private TextField taskTitleField;
  @FXML
  private ComboBox<String> dueDatePicker;
  @FXML
  private TextField categoryField;
  @FXML
  private TextField descriptionArea;
  @FXML
  private Button addButton;

  private Stage stage;
  private WeekviewController weekviewController;
  private AppContext appContext;

  /**
   * Constructs a NewTaskController.
   *
   * @param appContext the application context
   */
  public NewTaskController(AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }

  /**
   * Initializes the NewTaskController.
   */
  @FXML
  public void initialize() {
    dueDatePicker.getItems().addAll("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday");
    addButton.setOnAction(event -> {
      String taskName = taskTitleField.getText();
      String description = descriptionArea.getText();
      String category = categoryField.getText();
      Object weekdayValue = dueDatePicker.getValue();

      if (taskName == null || taskName.isEmpty() || weekdayValue == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please make sure Task Name and Weekday are filled!");
        alert.showAndWait();
      } else {
        Weekday weekday = Weekday.valueOf(weekdayValue.toString().toUpperCase());
        new SubmitTaskCommand(taskName, description, weekday, category, appContext).execute();
      }
    });
  }
}
