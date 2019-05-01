package application;


    
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class GUIQuizEnd extends Scene {
    public GUIQuizEnd(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      //Primary.setTitle("Results");
      BorderPane parent = (BorderPane)root;
      
      Label title = new Label("End of Quiz");
      
      HBox center = new HBox(10);
      Label text = new Label("You got ");
      Label correct = new Label(10 + "");
      parent.setTop(title);
      
      Button button = new Button("Home");
      parent.setCenter(button);
      button.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
    
}
