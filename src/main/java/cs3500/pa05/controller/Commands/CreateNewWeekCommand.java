package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.controller.PopUpController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The CreateNewWeekCommand class represents a command for creating a new week.
 * It is executed by switching to the new week screen.
 */
public class CreateNewWeekCommand implements Command {

  private final Stage stage;
  private AppContext appContext;

  /**
   * Constructs a CreateNewWeekCommand with the specified AppContext.
   *
   * @param appContext the application context
   */
  public CreateNewWeekCommand(AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }

  /**
   * Executes the CreateNewWeekCommand by creating a new week and switching to the new week screen.
   */
  @Override
  public void execute() {
    createNewButton();
  }

  /**
   * Creates a new week by switching to the new week screen.
   */
  private void createNewButton() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("popup.fxml"));
      loader.setController(new PopUpController(appContext));
      Scene s = loader.load();
      stage.setScene(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
