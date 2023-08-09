package cs3500.pa05.controller;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The implementation of the GUI controller.
 */
public class GuiControllerImpl implements Controller {

  @FXML
  private Button openExisting;
  @FXML
  private TextField filePathTextField;
  private FileHandler fh;

  /**
   * Constructs a GuiControllerImpl.
   */
  public GuiControllerImpl() {
    fh = new FileHandler();
  }

  /**
   * Initializes the GUI controller.
   */
  @FXML
  public void initialize() {
    this.openExisting.setOnAction(event -> openFileChooser());
    this.filePathTextField.setOnAction(event -> handleEnteredFilePath());
  }

  /**
   * Opens the file chooser dialog.
   */
  private void openFileChooser() {
    FileChooser fc = new FileChooser();
    Stage stage = new Stage();
    fc.setTitle("Open Existing bujo file");
    fc.setInitialDirectory(new File(System.getProperty("user.home")));
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("bujo files", "*.bujo"));
    File selectedFile = fc.showOpenDialog(stage);

    if (selectedFile != null) {
      String filePath = selectedFile.getAbsolutePath();
      if (filePath.endsWith(".bujo")) {
        fh.readOpenFile(new File(filePath));
        // Set WeekView Board
      } else {
        filePathTextField.setText("Please select a .bujo file");
      }
    }
  }

  /**
   * Handles the entered file path in the text field.
   */
  private void handleEnteredFilePath() {
    String userInput = filePathTextField.getText();
    if (isValidDirectory(userInput)) {
      // Do something
      System.out.println("Testing text field");
    } else {
      filePathTextField.setText("Please enter a valid directory");
    }
  }

  /**
   * Checks if the entered file path is a valid directory.
   *
   * @param filePath the entered file path
   * @return true if the file path is a valid directory, false otherwise
   */
  private boolean isValidDirectory(String filePath) {
    File file = new File(filePath);
    return file.isDirectory();
  }

  /**
   * Returns the stage of the application.
   *
   * @return the stage
   */
  private Stage getStage() {
    return (Stage) openExisting.getScene().getWindow();
  }
}
