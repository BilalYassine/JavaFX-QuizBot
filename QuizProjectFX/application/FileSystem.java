package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Handles all the file picking in the application
 * @author Evan Hansen
 */
public class FileSystem
{
    private final static FileChooser fileChooser = new FileChooser();

    /**
     * Allows user to select their JSON file to load, only allows files with .json extension to be selected
     * @param stage     Current window to render FileChooser on
     */
    public static void loadFile(Stage stage)
    {
        fileChooser.setTitle("Select Quiz Questions");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json*"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            try
            {
                FileManager.parseJSON(file);
            } catch (IOException | ParseException e)
            {
                // If error, skip reading rest of file
            }
        } 
    }

    /**
     * Allows user to pick where to save JSON file of all questions in database, and what to name it
     * @param stage     Current window to render FileChooser on
     */
    public static void saveFile(Stage stage)
    {
        fileChooser.setTitle("Save JSON");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json*"));
        File file = fileChooser.showSaveDialog(stage);
        if (!file.getName().contains("."))      // adds .json extension if user hasn't already added one
        {
            file = new File(file.getAbsolutePath() + ".json");
        }

        if (file != null)
        {
            try
            {
                FileWriter writer = new FileWriter(file);
                JSONObject JSONFile = WriteQuestionToJSON.generateJSONObject();
                writer.write(JSONFile.toJSONString());      // Writes questions to JSON file
                writer.close();
            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Allows user to select an image that will be shown with the question they have added
     * @param stage     Current window to render FileChooser on
     * @return Absolute FilePath of image
     */
    public static String loadImage(Stage stage)
    {
        fileChooser.setTitle("Select Question Image");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") ) );
        fileChooser.getExtensionFilters().addAll(                               // Adds extension filter options to file chooser
                new FileChooser.ExtensionFilter("All Files", "*.*"), 
                new FileChooser.ExtensionFilter("JPG", "*.jpg*"),
                new FileChooser.ExtensionFilter("PNG", "*.PNG*"));
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            return file.getAbsolutePath();
        }
        return null;
    }
}
