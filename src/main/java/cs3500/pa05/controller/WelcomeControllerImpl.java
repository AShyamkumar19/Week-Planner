package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.Command;
import cs3500.pa05.controller.Commands.CreateNewWeekCommand;
import cs3500.pa05.controller.Commands.OpenDirectoryCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the welcome window.
 */
public class WelcomeControllerImpl implements Controller {

  @FXML
  private Button openExisting; // needs
  @FXML
  private TextField filePathTextField;
  @FXML
  private Button createNew;

  private String pathName;
  private Stage stage;

  private AppContext appContext;

  /**
   * Constructs a WelcomeControllerImpl with the specified AppContext.
   *
   * @param appContext the AppContext
   */
  public WelcomeControllerImpl(AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }


  /**
   * set the two buttons
   */
  @FXML
  public void initialize() {
    Command openFileChooserCommand = new OpenDirectoryCommand(filePathTextField, appContext);
    this.openExisting.setOnAction(event -> openFileChooserCommand.execute());

    Command handleFilePathCommand = new CreateNewWeekCommand(appContext);
    this.createNew.setOnAction(event -> handleFilePathCommand.execute());
  }


}