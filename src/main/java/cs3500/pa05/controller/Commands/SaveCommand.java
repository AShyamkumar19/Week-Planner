package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.model.JsonUtils;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

/**
 * The SaveCommand class represents a command for saving the current week to a file.
 * It is executed by displaying a directory chooser dialog to select the save directory.
 */
public class SaveCommand implements Command {

  private final JsonUtils ju = new JsonUtils();
  private final AppContext appContext;
  private final MouseEvent event;

  /**
   * Constructs a SaveCommand with the specified application context and mouse event.
   *
   * @param appContext the application context
   * @param event the mouse event
   */
  public SaveCommand(AppContext appContext, MouseEvent event) {
    this.appContext = appContext;
    this.event = event;
  }

  /**
   * Executes the SaveCommand by displaying a directory chooser dialog to select the save directory
   * and saving the current week to a file.
   */
  @Override
  public void execute() {
    saveToFile(event);
  }

  /**
   * Displays a directory chooser dialog to select the save directory
   * and saves the current week to a file.
   *
   * @param event the mouse event that triggered the command
   */
  @FXML
  private void saveToFile(MouseEvent event) {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Select a Directory");
    File selectedDirectory =
        directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow());

    if (selectedDirectory == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "No Directory selected");
      alert.showAndWait();
    } else {
      ju.saveFile(appContext.getWeek(), selectedDirectory.toString());
      Alert alert =
          new Alert(Alert.AlertType.INFORMATION, "Saved to: "
              +
              selectedDirectory.getAbsolutePath());
      alert.showAndWait();
    }
  }
}
