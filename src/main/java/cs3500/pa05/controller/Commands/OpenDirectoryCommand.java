package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.controller.FileHandler;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The OpenDirectoryCommand class represents a command for opening a
 * directory and loading a .bujo file. It is executed by displaying a file
 * chooser dialog to select the .bujo file and loading the week view scene.
 */
public class OpenDirectoryCommand implements Command {

  private final TextField filePathTextField;
  private final Stage stage;
  private AppContext appContext;

  /**
   * Constructs an OpenDirectoryCommand with the specified text field and application context.
   *
   * @param filePathTextField the text field to display the selected file path
   * @param appContext        the application context
   */
  public OpenDirectoryCommand(TextField filePathTextField, AppContext appContext) {
    this.appContext = appContext;
    this.filePathTextField = filePathTextField;
    this.stage = appContext.getStage();
  }

  /**
   * Executes the OpenDirectoryCommand by displaying a file chooser dialog to select the .bujo file
   * and loading the week view scene.
   */
  @Override
  public void execute() {
    openFileChooser();
  }

  /**
   * Displays a file chooser dialog to select the .bujo file and loads the week view scene.
   */
  private void openFileChooser() {
    FileChooser fc = new FileChooser();
    fc.setTitle("Open Existing .bujo file");
    fc.setInitialDirectory(new File(System.getProperty("user.home")));
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("bujo files", "*.bujo"));
    File selectedFile = fc.showOpenDialog(stage);

    if (selectedFile != null) {
      String filePath = selectedFile.getAbsolutePath();
      if (filePath.endsWith(".bujo")) {
        System.out.println("Testing button");
        FileHandler fileHandler = new FileHandler();
        Week w = fileHandler.readOpenFile(new File(filePath));

        try {
          // Load and show the loading screen first
          FXMLLoader loader =
              new FXMLLoader(getClass().getClassLoader().getResource("loadingScreen.fxml"));
          Scene loadingScene = loader.load();
          stage.setScene(loadingScene);
          stage.setTitle("Loading...");

          // Pause for 2 seconds
          PauseTransition pause = new PauseTransition(Duration.seconds(2));

          pause.setOnFinished(e -> {
            try {
              // Now load and show the week view
              FXMLLoader weekLoader =
                  new FXMLLoader(getClass().getClassLoader().getResource("weekView.fxml"));
              appContext.setWeek(w);
              appContext.setWvc();
              weekLoader.setController(appContext.getWvc());
              stage.setTitle(w.getWeekName());
              Scene weekScene = weekLoader.load();
              stage.setScene(weekScene);
            } catch (IOException ex) {
              ex.printStackTrace();
            }
          });

          pause.play();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}