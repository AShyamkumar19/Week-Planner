package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * The SubmitTaskCommand class represents a command for submitting a new task.
 * It is executed by creating a new task with the provided information and adding
 * it to the application context.
 */
public class SubmitTaskCommand implements Command {
  private final String taskName;
  private final String description;
  private final Weekday weekday;
  private final String category;
  private final AppContext appContext;
  private final Stage stage;

  /**
   * Constructs a SubmitTaskCommand with the specified task information and application context.
   *
   * @param taskName    the name of the task
   * @param description the description of the task
   * @param weekday     the weekday of the task
   * @param category    the category of the task
   * @param appContext  the application context
   */
  public SubmitTaskCommand(String taskName, String description, Weekday weekday, String category,
                           AppContext appContext) {
    this.taskName = taskName;
    this.description = description;
    this.weekday = weekday;
    this.category = category;
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }

  /**
   * Executes the SubmitTaskCommand by creating a new task with the provided information
   * and adding it to the application context.
   */
  @Override
  public void execute() {
    try {
      Task t = new Task(taskName, description, weekday, category);
      appContext.addTask(t);
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("weekView.fxml"));
      loader.setController(appContext.getWvc());
      Scene s = loader.load();
      stage.setScene(s);
    } catch (IllegalArgumentException e) {
      // Create an alert dialog to show the error
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Exceeds the Max Number of Tasks for this Day");
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
    } catch (IOException e) {
      // Handle the IOException here
      Alert ioAlert = new Alert(Alert.AlertType.ERROR);
      ioAlert.setTitle("Loading Error");
      ioAlert.setHeaderText(null);
      ioAlert.setContentText("Failed to load the FXML file: " + e.getMessage());
      ioAlert.showAndWait();
    }
  }
}

