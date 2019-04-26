package application;
    
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GUITopicSelectController extends Scene {
    public GUITopicSelectController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      //Primary.setTitle("Topic Select");
      BorderPane parent = (BorderPane) root;
      this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      VBox vbox = new VBox();

      Label titleLabel = new Label("Select a Topic:");
      titleLabel.setTranslateY(100);
      titleLabel.setId("title-text");
      //titleLabel.setFont(new Font("Arial", 55));

      vbox.setSpacing(10);
      vbox.getChildren().add((titleLabel));
      vbox.setAlignment(Pos.CENTER);
      
      ObservableList<String> options = 
		FXCollections.observableArrayList(
		    "Math",
		    "English",
		    "History"
		);
      ComboBox topicSelectBox = new ComboBox(options);
      topicSelectBox.setTranslateY(100);
      
      vbox.getChildren().add(topicSelectBox);
      
      Label questionsLabel = new Label("# of Questions (1-5) : ");
      TextField questionsText = new TextField();
      HBox questions = new HBox(8);
      questions.getChildren().addAll(questionsLabel,questionsText);
      questions.setTranslateY(100);
      questions.setAlignment(Pos.CENTER);
      
      vbox.getChildren().add(questions);
      
      parent.setTop(vbox);
      
      Button button = new Button("Take Quiz");
      parent.setCenter(button);
      button.setOnAction(e ->Primary.setScene(sceneList.get(1)));
  }
}