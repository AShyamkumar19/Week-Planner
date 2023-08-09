package cs3500.pa05;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.view.WelcomeGuiView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents a driver.
 */
public class Driver extends Application {


  /**
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    AppContext appContext = new AppContext(primaryStage);

    WelcomeGuiView view = new WelcomeGuiView(appContext);
    try {
      primaryStage.setScene(view.load());
      primaryStage.setTitle("Welcome Screen");
      primaryStage.show();
    } catch (IllegalStateException exc) {
      System.err.println(exc.getMessage());
      System.exit(1);
    }
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch();
  }
}
