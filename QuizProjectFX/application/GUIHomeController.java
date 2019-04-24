package application;


    
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class GUIHomeController extends Scene {
    public GUIHomeController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      BorderPane parent = (BorderPane)root;
      this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      HBox titleBox = new HBox(10);
      Label title = new Label("Quiz Generator!");
      title.setId("title-text");
      titleBox.getChildren().addAll(title);
      titleBox.setAlignment(Pos.CENTER);
      parent.setCenter(titleBox);
      
      parent.setPadding(new Insets(10, 10, 10, 10));
      HBox buttons = new HBox(10);
      buttons.setAlignment(Pos.CENTER);
      Button quizButton = new Button("Take Quiz");
      quizButton.setOnAction(e ->Primary.setScene(sceneList.get(1)));
      quizButton.setPrefSize(100,100);
      
      final FileChooser fileChooser = new FileChooser();
      Button loadQuiz = new Button("Load Quiz");
      loadQuiz.setPrefSize(100,100);
      loadQuiz.setOnAction(e -> {
        File file = fileChooser.showOpenDialog(Primary);
        if (file != null) {
            System.out.println("NOT NULL");
        }
      });
      Button addQuestionButton = new Button("Add Question");
      addQuestionButton.setOnAction(e ->Primary.setScene(sceneList.get(2)));
      addQuestionButton.setPrefSize(100,100);
      
      buttons.getChildren().addAll(quizButton,loadQuiz,addQuestionButton);
      parent.setBottom(buttons);
  }
    
}

