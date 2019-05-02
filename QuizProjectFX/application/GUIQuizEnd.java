package application;


    
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
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
      this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      Primary.setTitle("Results");
      BorderPane parent = (BorderPane)root;
      HBox titleBox = new HBox(10);
      Label title = new Label("End of Quiz");
      title.setId("title-text");
      titleBox.getChildren().addAll(title);
      titleBox.setAlignment(Pos.CENTER);
      parent.setTop(titleBox);
      
      HBox center = new HBox(10);
      Label text = new Label("You got ");
      text.setId("subtitle-text");
      Label correct = new Label(numCorrect[0] + "");
      correct.setId("title-text");
      Label text2 = new Label(" Correct!");
      text2.setId("subtitle-text");
      center.getChildren().addAll(text,correct,text2);
      center.setAlignment(Pos.CENTER);
      parent.setCenter(center);
      
      Button homeButton = new Button("Home");
      homeButton.setPrefSize(100, 100);
      parent.setBottom(homeButton);
      homeButton.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
    
}
