package application;


    
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GUIAddQuestionController extends Scene {
    public GUIAddQuestionController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      BorderPane parent = (BorderPane)root;
      parent.setPadding(new Insets(10, 10, 10, 10));
      HBox bottom = new HBox(8);
      
      Button returnButton = new Button("Return");
      Button saveButton = new Button("Save Question");
      Button addButton = new Button("Add Another Question");
      bottom.getChildren().addAll(returnButton,saveButton,addButton);
      
      parent.setBottom(bottom);
      
      Label questionLabel = new Label("Question");
      TextField questionText = new TextField();
      HBox question = new HBox(8);
      question.getChildren().addAll(questionLabel,questionText);
      
      VBox choices = new VBox(10);
      Label topicLabel = new Label("Topic");
      TextField topicText = new TextField();
      HBox topics = new HBox(8);
      topics.getChildren().addAll(topicLabel,topicText);
      
      Label correctLabel = new Label("Correct Answer");
      TextField correctText = new TextField();
      HBox correct = new HBox(8);
      correct.getChildren().addAll(correctLabel,correctText);
      
      Label answerLabel1 = new Label("Wrong Answer 1");
      TextField answerText1 = new TextField();      
      HBox answer1 = new HBox(8);
      answer1.getChildren().addAll(answerLabel1,answerText1);
      
      Label answerLabel2 = new Label("Wrong Answer 2");
      TextField answerText2 = new TextField();
      HBox answer2 = new HBox(8);
      answer2.getChildren().addAll(answerLabel2,answerText2);
      
      Label answerLabel3 = new Label("Wrong Answer 3");
      TextField answerText3 = new TextField();      
      HBox answer3 = new HBox(8);
      answer3.getChildren().addAll(answerLabel3,answerText3);
      
      Label answerLabel4 = new Label("Wrong Answer 4");
      TextField answerText4 = new TextField();
      HBox answer4 = new HBox(8);
      answer4.getChildren().addAll(answerLabel4,answerText4);
      
      choices.getChildren().addAll(topics,correct,answer1,answer2,answer3,answer4);
      parent.setCenter(choices);
      returnButton.setOnAction(e ->Primary.setScene(sceneList.get(0)));
      
  }
}