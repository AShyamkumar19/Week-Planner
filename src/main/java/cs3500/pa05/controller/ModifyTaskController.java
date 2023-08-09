package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.SaveTaskChangesCommand;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for editing tasks
 */
public class ModifyTaskController implements Controller {

  @FXML
  private TextField taskTitleField;
  @FXML
  private ComboBox<String> dueDatePicker;
  @FXML
  private TextField categoryField;
  @FXML
  private TextField descriptionArea;
  @FXML
  private Button saveChanges;
  @FXML
  private CheckBox checkBox;

  private Stage stage;

  private WeekviewController weekviewController;

  private AppContext appContext;

  private Task task;

  /**
   *
   * @param appContext
   * @param task
   *
   */
  public ModifyTaskController(AppContext appContext, Task task) {
    this.stage = appContext.getStage();
    this.appContext = appContext;
    this.task = task;
  }
  @Override
  public void initialize() {

    dueDatePicker.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    dueDatePicker.setValue(task.getDayOfWeek().toString());
    taskTitleField.setText(task.getName());
    descriptionArea.setText(task.getDescription());
    categoryField.setText(task.getCategory());
    checkBox.setSelected(false);
    

    this.saveChanges.setOnMouseClicked(event -> {
      String taskName = taskTitleField.getText();
      String taskDescription = descriptionArea.getText();
      String taskCategory = categoryField.getText();
      String taskDate = dueDatePicker.getValue();
      boolean isComplete = checkBox.isSelected();

      // Check if the required fields are filled
      if (taskName == null || taskDescription == null || taskDate == null) {
        // Alert the user that they need to fill out all fields
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Input Error");
        alert.setContentText("Please fill out all fields before submitting.");
        alert.showAndWait();
        return;
      }
      Weekday weekday = Weekday.valueOf(taskDate.toUpperCase());

      SaveTaskChangesCommand command
          = new SaveTaskChangesCommand(appContext, taskName, taskDescription, weekday,
          taskCategory, this.task);
      command.execute();
    });
  }
}
