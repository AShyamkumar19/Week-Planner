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
 * Actions for save changes button in task change
 */
public class SaveTaskChangesCommand implements Command {
  private Stage stage;
  private Weekday weekday;
  private String description;
  private String category;
  private AppContext appContext;
  private Task task;

  /**
   *
   * @param appContext app context
   * @param taskName name of task
   * @param description task description
   * @param weekday day of week
   * @param category task category
   * @param task task before edits
   */
  public SaveTaskChangesCommand(AppContext appContext,
                                String taskName, String description,
                                Weekday weekday, String category, Task task) {
    this.appContext = appContext;

    this.stage = appContext.getStage();
    this.weekday = weekday;
    this.description = description;
    this.category = category;
    this.task = task;

  }


  /**
   * Executes the command.
   */
  @Override
  public void execute() {
    createAndReplace();
  }

  private void createAndReplace() {
    try {
      appContext.removeTodo(task);
      Task t = new Task(task.getName(), description, weekday, category);
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
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
