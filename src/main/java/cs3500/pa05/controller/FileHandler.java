package cs3500.pa05.controller;

import cs3500.pa05.model.JsonUtils;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Handles incoming file
 */
public class FileHandler {

  private JsonUtils ju;

  /**
   *
   * @param ju - instance
   */
  public FileHandler(JsonUtils ju) {
    this.ju = ju;
  }

  /**
   * FileHandler Constructor
   */
  public FileHandler() {}

  /**
   *
   * @param path
   * @return Week
   */
  public Week readOpenFile(File path) {
    try {
      ju = new JsonUtils();
      String filePath = new Scanner(path).useDelimiter("\\Z").next();
      return ju.openFile(filePath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
