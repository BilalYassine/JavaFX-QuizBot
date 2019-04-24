package application;


    
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class GUIQuizController extends Scene {
    public GUIQuizController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      //Primary.setTitle("Quiz Time");
      
      Button button = new Button("Home");
      ((BorderPane) root).setCenter(button);
      button.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
    
}
