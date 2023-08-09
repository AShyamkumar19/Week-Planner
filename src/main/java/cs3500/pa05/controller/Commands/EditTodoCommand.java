package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.controller.ModifyEventController;
import cs3500.pa05.controller.ModifyTaskController;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Todo;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Edits or shows the ability for user to edit Event or task
 */
public class EditTodoCommand implements Command {
  private Todo todo;
  private AppContext appContext;
  private Stage stage;

  /**
   * @param todo the type of to-do
   * @param appContext the controller
   */
  public EditTodoCommand(Todo todo, AppContext appContext) {
    this.todo = todo;
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }

  /**
   * Executes the command.
   */
  @Override
  public void execute() {
    editTodoScreen();
  }

  private void editTodoScreen() {
    if (todo instanceof Task) {
      try {
        Task t = (Task) todo;

        /// creat constructor for this
        ModifyTaskController mtc = new ModifyTaskController(appContext, t);
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("modifyTask.fxml"));
        loader.setController(mtc);
        Scene s = loader.load();
        // Create a new stage for the modifyEvent screen
        Stage stage = appContext.getStage();

        // Set the scene on the new stage
        stage.setScene(s);

        // Show the new stage
        stage.show();
      } catch (IOException exception) {
        exception.printStackTrace();
        // You can add more sophisticated error handling here.
        // For example, show an error dialog to the user.
      }
    } else {
      try {
        Event e = (Event) todo;

        ModifyEventController mec = new ModifyEventController(appContext, e);
        FXMLLoader loader =
            new FXMLLoader(getClass().getClassLoader().getResource("modifyEvent.fxml"));
        loader.setController(mec);
        Scene s = loader.load();
        // Create a new stage for the modifyEvent screen
        Stage stage = appContext.getStage();

        // Set the scene on the new stage
        stage.setScene(s);

        // Show the new stage
        stage.show();
      } catch (IOException exception) {
        exception.printStackTrace();
        // You can add more sophisticated error handling here.
        // For example, show an error dialog to the user.
      }
    }
  }

}
