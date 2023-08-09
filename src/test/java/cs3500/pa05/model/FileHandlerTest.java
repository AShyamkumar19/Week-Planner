package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.controller.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

public class FileHandlerTest {

  @Mock
  private JsonUtils jsonUtils;

  private FileHandler fileHandler;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.fileHandler = new FileHandler(jsonUtils);
  }

//  @Test
//  public void testReadOpenFile() throws FileNotFoundException {
//    String filePath = "src/test/java/testFiles/test.bujo";
//    File file = new File(filePath);
//
//    Week expectedWeek = new Week("SerializingIssues", null, 0, 0, null, null, null); // create expectedWeek object here
//    when(jsonUtils.openFile(filePath)).thenReturn(expectedWeek);
//
//    Week result = fileHandler.readOpenFile(file);
//
//    verify(jsonUtils, times(1)).openFile(filePath);
//    assertEquals(expectedWeek, result);
//  }

  @Test
  public void testReadOpenFileWithException() {
    File file = new File("nonexistent_file.json");

    assertThrows(RuntimeException.class, () -> fileHandler.readOpenFile(file));
  }
}
