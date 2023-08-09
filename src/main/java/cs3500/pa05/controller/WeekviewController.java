package cs3500.pa05.controller;

import cs3500.pa05.controller.Commands.CreateTodoCommand;
import cs3500.pa05.controller.Commands.EditTodoCommand;
import cs3500.pa05.controller.Commands.SaveCommand;
import cs3500.pa05.model.Todo;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.Weekday;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * this is the controller for our main window
 */
public class WeekviewController implements Controller {

  @FXML
  private Button saveButton;

  @FXML
  private Button createTodo;

  @FXML
  private Button deleteTodo;
  @FXML
  private Text weekTitleFxml;
  @FXML
  private ListView<Hyperlink> linkHouse;
  @FXML
  private ListView<String> todoList;
  @FXML
  private ListView<String> sundayList;
  @FXML
  private ListView<String> mondayList;
  @FXML
  private ListView<String> tuesdayList;
  @FXML
  private ListView<String> wednesdayList;
  @FXML
  private ListView<String> thursdayList;
  @FXML
  private ListView<String> fridayList;
  @FXML
  private ListView<String> saturdayList;
  @FXML
  private TextArea quotesField;
  @FXML
  private TextArea notesField;

  @FXML
  AnchorPane anchorPane;
  private ObservableList<String> todoItems;

  private ObservableList<String> sundayItems;

  private ObservableList<String> mondayItems;

  private ObservableList<String> tuesdayItems;

  private ObservableList<String> wednesdayItems;

  private ObservableList<String> thursdayItems;

  private ObservableList<String> fridayItems;

  private ObservableList<String> saturdayItems;

  private Stage stage;
  private Week week;
  private AppContext appContext;

  /**
   * Constructs a WeekviewController with the specified AppContext.
   *
   * @param appContext the AppContext
   */
  public WeekviewController(AppContext appContext) {
    this.appContext = appContext;
    this.stage = appContext.getStage();
    this.week = appContext.getWeek();
    this.todoItems = FXCollections.observableArrayList();
    this.sundayItems = FXCollections.observableArrayList();
    this.mondayItems = FXCollections.observableArrayList();
    this.tuesdayItems = FXCollections.observableArrayList();
    this.wednesdayItems = FXCollections.observableArrayList();
    this.thursdayItems = FXCollections.observableArrayList();
    this.fridayItems = FXCollections.observableArrayList();
    this.saturdayItems = FXCollections.observableArrayList();
  }

  /**
   * Initializes the controller after the FXML file is loaded.
   */
  @FXML
  public void initialize() {
    this.createTodo.setOnMouseClicked(event -> new CreateTodoCommand(appContext).execute());
    this.deleteTodo.setOnMouseClicked(event -> {
      // Code to execute when the delete button is clicked
      deleteTodo();

      // Consume the event so it does not bubble up to the root
      event.consume();
    });
    this.saveButton.setOnMouseClicked(event -> new SaveCommand(appContext, event).execute());

    notesField.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        String note = notesField.getText().trim(); // Removes trailing newline

        // Check if the note contains http or https
        if (note.contains("http://") || note.contains("https://")) {
          // The note contains a URL, make it a hyperlink
          Hyperlink link = new Hyperlink(note);

          link.setOnAction(e -> {
            try {
              java.awt.Desktop.getDesktop().browse(new java.net.URI(note));
            } catch (IOException | URISyntaxException e1) {
              e1.printStackTrace();
            }
          });

          linkHouse.getItems().add(link); // This will add the link to your ListView named linkHouse.
          notesField.clear(); // Clear the TextArea
        } else {
          // The note does not contain a URL, just add it as a note
          appContext.addNote(note);
          event.consume();
        }
      }
    });

    quotesField.setOnKeyReleased(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        String quote = quotesField.getText().trim();  // Removes trailing newline
        appContext.addQuote(quote);
        event.consume();
      }
    });


    this.todoList.setOnMouseClicked(event -> {

      if (event.getClickCount() == 2) { // Double click
        String selectedTodoString = todoList.getSelectionModel().getSelectedItem();
        Todo selectedTodo = null;

        // Loop through your todo list and find the one that matches the string
        for (Todo t : week.getTodoList()) {
          if (t.toString().equals(selectedTodoString)) {
            selectedTodo = t;
            break;
          }
        }

        // If we found a matching Todo, execute the command
        if (selectedTodo != null) {
          new EditTodoCommand(selectedTodo, appContext).execute();
        }
      }
    });

    // create a new DropShadow effect
    DropShadow dropShadow = new DropShadow();
    dropShadow.setColor(Color.RED);
    dropShadow.setRadius(40.0);
    dropShadow.setHeight(40.0);
    dropShadow.setWidth(40.0);

    // listen for changes to the selected item in the ListView
    todoList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        // if an item is selected, add the glow effect to the delete button
        deleteTodo.setEffect(dropShadow);
      } else {
        // if no item is selected, remove the glow effect from the delete button
        deleteTodo.setEffect(null);
      }
    });

    quotesField.setText(String.join("\n", appContext.getWeek().getQuotes()));

    assignTodoItems();
    this.todoList.setItems(todoItems);
    this.sundayList.setItems(sundayItems);
    this.mondayList.setItems(mondayItems);
    this.tuesdayList.setItems(tuesdayItems);
    this.wednesdayList.setItems(wednesdayItems);
    this.thursdayList.setItems(thursdayItems);
    this.fridayList.setItems(fridayItems);
    this.saturdayList.setItems(saturdayItems);
    todoList.setItems(todoItems);
    weekTitleFxml.setText(week.getWeekName());
    assignTodos();
  }

  private void deleteTodo() {
    // Get the selected to-do item as a string
    String selectedTodoString = todoList.getSelectionModel().getSelectedItem();

    if (selectedTodoString != null) {
      // Loop through your todo list and find the one that matches the string
      for (Todo t : week.getTodoList()) {
        if (t.toString().equals(selectedTodoString)) {
          // Once found, remove it from todoItems and from your AppContext
          todoItems.remove(selectedTodoString);
          appContext.removeTodo(t);
          // Remove it from the corresponding day list
          getDayItems(t.getDayOfWeek()).remove(selectedTodoString);
          break;
        }
      }
    }
  }

  private ObservableList<String> getDayItems(Weekday day) {
    switch (day) {
      case SUNDAY:
        return sundayItems;
      case MONDAY:
        return mondayItems;
      case TUESDAY:
        return tuesdayItems;
      case WEDNESDAY:
        return wednesdayItems;
      case THURSDAY:
        return thursdayItems;
      case FRIDAY:
        return fridayItems;
      case SATURDAY:
        return saturdayItems;
      default:
        return null;
    }
  }


  /**
   * adds all todos to the todolist
   */
  public void assignTodoItems() {
    this.todoItems.clear();
    for (Todo e : week.getTodoList()) {
      todoItems.add(e.toString());
    }
  }

  /**
   * Clears the week items and populates all the list with to-dos
   */
  public void assignTodos() {
    this.sundayItems.clear();
    this.mondayItems.clear();
    this.tuesdayItems.clear();
    this.wednesdayItems.clear();
    this.thursdayItems.clear();
    this.fridayItems.clear();
    this.saturdayItems.clear();

    for (Todo t : week.getTodoList()) {
      if (t.getDayOfWeek() == Weekday.SUNDAY) {
        this.sundayItems.add(t.toString());
      } else if (t.getDayOfWeek() == Weekday.MONDAY) {
        this.mondayItems.add(t.toString());
      } else if (t.getDayOfWeek() == Weekday.TUESDAY) {
        this.tuesdayItems.add(t.toString());
      } else if (t.getDayOfWeek() == Weekday.WEDNESDAY) {
        this.wednesdayItems.add(t.toString());
      } else if (t.getDayOfWeek() == Weekday.THURSDAY) {
        this.thursdayItems.add(t.toString());
      } else if (t.getDayOfWeek() == Weekday.FRIDAY) {
        this.fridayItems.add(t.toString());
      } else if (t.getDayOfWeek() == Weekday.SATURDAY) {
        this.saturdayItems.add(t.toString());
      }
    }
  }




}
