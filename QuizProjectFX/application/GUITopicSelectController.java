package application;
    
import java.util.List;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class GUITopicSelectController extends Scene {
    public GUITopicSelectController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      //Primary.setTitle("Topic Select");
      BorderPane parent = (BorderPane) root;
      
      HBox hbox = new HBox();

      Label titleLabel = new Label("Select a Topic:");
      titleLabel.setTranslateY(100);
      titleLabel.setFont(new Font("Arial", 55));

      hbox.setSpacing(10);
      hbox.getChildren().add((titleLabel));
      hbox.setAlignment(Pos.CENTER);

      
      parent.setTop(hbox);
      
      Button button = new Button("Home");
      parent.setCenter(button);
      button.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
}