package cs3500.pa05.controller.Commands;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.model.Week;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The SubmitWeekCommand class
 * represents a command for submitting a new week with specified specifications.
 * It is executed by creating a new week
 * with the provided specifications and updating the application context.
 */
public class SubmitWeekCommand implements Command {

  private final Stage stage;
  private final int maxTask;
  private final int maxEvent;
  private final String weekName;
  private final AppContext appContext;

  /**
   * Constructs a SubmitWeekCommand with the specified week specifications and application context.
   *
   * @param maxTask the maximum number of tasks allowed in the week
   * @param maxEvent the maximum number of events allowed in the week
   * @param weekName the name of the week
   * @param appContext the application context
   */
  public SubmitWeekCommand(int maxTask, int maxEvent, String weekName, AppContext appContext) {
    this.stage = appContext.getStage();
    this.maxTask = maxTask;
    this.maxEvent = maxEvent;
    this.weekName = weekName;
    this.appContext = appContext;
  }

  /**
   * Executes the SubmitWeekCommand by creating a new week with the provided specifications and updating the application context.
   */
  public void execute() {
    handleWeekSpecifications();
  }

  /**
   * Creates a new week with the provided specifications and updates the application context.
   */

  private void handleWeekSpecifications() {
    try {
      // Load and show the loading screen first
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loadingScreen.fxml"));
      Scene loadingScene = loader.load();
      stage.setScene(loadingScene);
      stage.setTitle("Loading...");

      // Pause for 2 seconds
      PauseTransition pause = new PauseTransition(Duration.seconds(2));

      pause.setOnFinished(e -> {
        try {
          // Now load and show the week view
          FXMLLoader weekLoader = new FXMLLoader(getClass().getClassLoader().getResource("weekView.fxml"));
          Week week = new Week(weekName, new ArrayList<>(), maxEvent, maxTask);
          appContext.setWeek(week);
          appContext.setWvc();
          weekLoader.setController(appContext.getWvc());
          Scene weekScene = weekLoader.load();
          stage.setTitle(weekName);
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
