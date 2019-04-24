package application;


    
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class GUIQuizEnd extends Scene {
    public GUIQuizEnd(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      BorderPane parent = (BorderPane)root;
      
      Label title = new Label("End of Quiz");
      parent.setTop(title);
      
      Button button = new Button("Home");
      parent.setCenter(button);
      button.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
    
}
