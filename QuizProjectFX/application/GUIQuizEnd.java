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
    public GUIQuizEnd(Parent root,Stage Primary, List<Scene> sceneList,int[] numCorrect) {
      super(root,800,600);
      Primary.setTitle("Results");
      BorderPane parent = (BorderPane)root;
      
      Label title = new Label("End of Quiz");
      parent.setTop(title);
      
      HBox center = new HBox(10);
      Label text = new Label("You got ");
      Label correct = new Label(numCorrect[0] + "");
      center.getChildren().addAll(text,correct);
      parent.setCenter(center);
      
      Button button = new Button("Home");
      parent.setBottom(button);
      button.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
    
}
