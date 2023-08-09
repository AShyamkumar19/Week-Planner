package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Todo;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for delete button, deletes a specific Todos
 */
public class DeleteTodoCommand implements Command {

  private final Stage stage;
  private Week week;
  private Todo todo;
  private Controller wvc;
  private AppContext appContext;


  /**
   * Constructs a CreateTodoCommand with the specified AppContext.
   *
   * @param appContext the application context
   */
  public DeleteTodoCommand(AppContext appContext, Todo todo) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
    this.todo = todo;
  }

  /**
   * calls the handleDeleteTodo() method
   */
  @Override
  public void execute() {
    handleDeleteTodo();
  }

  /**
   * Responsible for deleting the selected event or task
   */
  private void handleDeleteTodo() {
    try {
      appContext.removeTodo(todo);
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("weekView.fxml"));
      loader.setController(appContext.getWvc());
      Scene s = loader.load();
      stage.setScene(s);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
