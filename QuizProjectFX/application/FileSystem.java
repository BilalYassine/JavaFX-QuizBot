package application;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileSystem
{
    final static FileChooser fileChooser = new FileChooser();

    public static void loadFile(Stage stage)
    {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            // openFile(file); calls appropriate file import methods/class
        }
    }
}
