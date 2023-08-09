package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.NewEventController;
import cs3500.pa05.controller.NewTaskController;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * The CreateTodoCommand class represents a command for creating a new todo.
 * It is executed by displaying a dialog to select the todo type (event or task)
 * and loading the corresponding popup window.
 */
public class CreateTodoCommand implements Command {

  private final Stage stage;
  private Week week;
  private Controller wvc;
  private AppContext appContext;

  /**
   * Constructs a CreateTodoCommand with the specified AppContext.
   *
   * @param appContext the application context
   */
  public CreateTodoCommand(AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }

  /**
   * Executes the CreateTodoCommand by displaying a dialog to select the
   * todo type and loading the corresponding popup window.
   */
  @Override
  public void execute() {
    onCreateTodoClicked();
  }

  /**
   * Displays a dialog to select the todo type (event or task) and loads the corresponding
   * popup window.
   */
  @FXML
  private void onCreateTodoClicked() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Todo Type");
    alert.setHeaderText("Select a Todo Type");

    RadioButton eventButton = new RadioButton("Event");
    RadioButton taskButton = new RadioButton("Task");
    ToggleGroup group = new ToggleGroup();

    eventButton.setToggleGroup(group);
    taskButton.setToggleGroup(group);

    HBox hbox = new HBox(10, eventButton, taskButton);
    alert.getDialogPane().setContent(hbox);

    alert.showAndWait().ifPresent(response -> {
      if (group.getSelectedToggle() != null) {
        try {
          if (group.getSelectedToggle().equals(eventButton)) {
            FXMLLoader loader =
                new FXMLLoader(getClass().getClassLoader().getResource("newEventPopup.fxml"));
            loader.setController(new NewEventController(appContext));
            Scene s = loader.load();
            stage.setScene(s);
          } else if (group.getSelectedToggle().equals(taskButton)) {
            FXMLLoader loader =
                new FXMLLoader(getClass().getClassLoader().getResource("newTaskPopup.fxml"));
            loader.setController(new NewTaskController(appContext));
            Scene s = loader.load();
            stage.setTitle("New Task");
            stage.setScene(s);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
