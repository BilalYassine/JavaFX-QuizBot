package application;


    
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class GUIHomeController extends Scene {
    public GUIHomeController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      BorderPane parent = (BorderPane)root;
      
      parent.setPadding(new Insets(10, 10, 10, 10));
      HBox buttons = new HBox(10);
      buttons.setAlignment(Pos.CENTER);
      Button quizButton = new Button("Take Quiz");
      quizButton.setOnAction(e ->Primary.setScene(sceneList.get(1)));
      quizButton.setPrefSize(100,100);
      
      Button loadQuiz = new Button("Load Quiz");
      loadQuiz.setPrefSize(100,100);
      loadQuiz.setOnAction(
              new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(final ActionEvent e) {
                      FileSystem.loadFile(Primary);
                  }
              });
      
      Button addQuestionButton = new Button("Add Question");
      addQuestionButton.setOnAction(e ->Primary.setScene(sceneList.get(2)));
      addQuestionButton.setPrefSize(100,100);
      
      buttons.getChildren().addAll(quizButton,loadQuiz,addQuestionButton);
      parent.setBottom(buttons);
  }
    
}

