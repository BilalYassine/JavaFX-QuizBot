package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileSystem
{
    final static FileChooser fileChooser = new FileChooser();

    public static void loadFile(Stage stage)
    {
        fileChooser.setTitle("Select Quiz Questions");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json*"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            // openFile(file); calls appropriate file import methods/class
        }
    }

    public static void saveJSON(Stage stage)
    {
        fileChooser.setTitle("Save JSON");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json*"));
        File file = fileChooser.showSaveDialog(stage);
        if (!file.getName().contains("."))
        {
            file = new File(file.getAbsolutePath() + ".json");
        }

        if (file != null)
        {
            try
            {
                FileWriter writer = new FileWriter(file);
                // PrintWriter print = new PrintWriter(writer);
                writer.write("TEST JSON");      // Replace with JSON file Generator
                writer.close();
            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void loadImage(Stage stage)
    {
        fileChooser.setTitle("Select Question Image");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg*"),
                new FileChooser.ExtensionFilter("PNG", "*.PNG*"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            // openFile(file); calls appropriate methods for images
        }
    }
}
