package cs3500.pa05.view;

import cs3500.pa05.controller.AppContext;
import cs3500.pa05.controller.WelcomeControllerImpl;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * view for the welcome screen
 */
public class WelcomeGuiView implements View {
  FXMLLoader loader;

  /**
   *
   * @param appContext Controller
   */
  public WelcomeGuiView(AppContext appContext) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("welcomeScreen.fxml"));
    this.loader.setController(new WelcomeControllerImpl(appContext));
  }
  /**
   *
   * @return the layout
   */

  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      System.out.println(exc.getMessage());
      throw new IllegalStateException("Unable to load layout.");
    }
  }


}
