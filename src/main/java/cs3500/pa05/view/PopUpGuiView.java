package cs3500.pa05.view;

import cs3500.pa05.controller.PopUpController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a welcome GUI view.
 */
public class PopUpGuiView implements View {
  FXMLLoader loader;

  /**
   * Constructs a welcome GUI view.
   *
   * @param controller the controller
   */
  public PopUpGuiView (PopUpController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("popup.fxml"));
    this.loader.setController(controller);
  }

  /**
   *
   * @return scene
   * @throws IllegalStateException
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
