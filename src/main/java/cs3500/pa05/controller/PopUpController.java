package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.SubmitWeekCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the popup window.
 */
public class PopUpController implements Controller {

  @FXML
  private Slider taskSlider;
  @FXML
  private Slider eventSlider;
  @FXML
  private TextField weekName;
  @FXML
  private TextField taskValueLabel;
  @FXML
  private TextField eventValueLabel;

  @FXML
  private Button createButton;

  @FXML
  private Label nameWarning;

  private Stage stage;

  private AppContext appContext;

  /**
   * Constructor for the popup controller.
   */
  public PopUpController(AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
  }

  /**
   * Initializes the popup controller.
   */
  @FXML
  public void initialize() {
    taskSlider.setValue(1);
    eventSlider.setValue(1);
    nameWarning.setVisible(false);

    taskValueLabel.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == null || newValue.isEmpty()) {
        taskSlider.setValue(0);
      } else {
        try {
          int val = Integer.parseInt(newValue);
          taskSlider.setValue(val);
        } catch (NumberFormatException e) {
          taskValueLabel.setText(oldValue);
        }
      }
    });

    eventValueLabel.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == null || newValue.isEmpty()) {
        eventSlider.setValue(0);
      } else {
        try {
          int val = Integer.parseInt(newValue);
          eventSlider.setValue(val);
        } catch (NumberFormatException e) {
          eventValueLabel.setText(oldValue);
        }
      }
    });

    taskSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      taskValueLabel.setText(String.valueOf(newValue.intValue()));
    });

    eventSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      eventValueLabel.setText(String.valueOf(newValue.intValue()));
    });

    taskSlider.setOnMouseDragged(event -> {
      handleDrag(taskSlider);
      taskValueLabel.setText(String.valueOf(taskSlider.getValue()));
    });

    eventSlider.setOnMouseDragged(event -> {
      handleDrag(eventSlider);
      eventValueLabel.setText(String.valueOf(eventSlider.getValue()));
    });

    weekName.setOnAction(event -> {
      String userInput = weekName.getText();
      weekName.setText(userInput);
    });

    createButton.setOnAction(event -> {
      int taskVal = (int) taskSlider.getValue();
      int eventVal = (int) eventSlider.getValue();
      new SubmitWeekCommand(taskVal, eventVal, weekName.getText(), appContext).execute();
    });
  }

  /**
   * Handles the dragging of the slider.
   *
   * @param s the slider
   */
  public void handleDrag(Slider s) {
    s.adjustValue(s.getValue() + 1);
  }
}
