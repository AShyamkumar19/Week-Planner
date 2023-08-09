package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Interface for loading scene
 */
public interface View {
  /**
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}

