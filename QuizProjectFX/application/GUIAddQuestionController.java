package application;


    
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class GUIAddQuestionController extends Scene {
    public GUIAddQuestionController(Parent root,Stage Primary, List<Scene> sceneList) {
      super(root,800,600);
      BorderPane parent = (BorderPane)root;
      this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      parent.setPadding(new Insets(10, 10, 10, 10));

      final FileChooser fileChooser = new FileChooser();
      ArrayList<String> image = new ArrayList<String>();
      image.add(null);
      Button addImage = new Button("Add Image");
      parent.setRight(addImage);
      addImage.setOnAction(e -> {
        File file = fileChooser.showOpenDialog(Primary);
        if (file != null) {
            image.set(0, file.getAbsolutePath());
        }
      });
      VBox choices = new VBox(10);
      
      Label questionLabel = new Label("Question");
      TextField questionText = new TextField();
      HBox question = new HBox(8);
      question.getChildren().addAll(questionLabel,questionText);
      

      Label topicLabel = new Label("Topic");
      TextField topicText = new TextField();
      HBox topics = new HBox(8);
      topics.getChildren().addAll(topicLabel,topicText);
      
      HBox meta = new HBox(8);
      Label metaLabel = new Label("Meta Data");
      TextField metaText = new TextField();
      meta.getChildren().addAll(metaLabel,metaText);
      
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
      
      HBox titleBox = new HBox(10);
      Label title = new Label("Add a Question");
      title.setId("title-text");
      titleBox.setAlignment(Pos.CENTER);
      titleBox.getChildren().addAll(title);
      parent.setTop(titleBox);
      
      choices.getChildren().addAll(question,topics,meta,correct,answer1,answer2,answer3,answer4);
      parent.setCenter(choices);
      
      
      HBox bottom = new HBox(8);
      
      Button returnButton = new Button("Return");
      Button saveButton = new Button("Save Question");
      bottom.getChildren().addAll(returnButton,saveButton);
      
      parent.setBottom(bottom);
      saveButton.setOnAction(e -> {
        Question q = new Question();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        if(questionText.getText().equals("")) {
          alert.setHeaderText("You forgot to add a Question");
          alert.setContentText("Ooops, please add text to the question box");
          alert.showAndWait();
          return;
        }
        q.setQuestion(questionText.getText());
        if(topicText.getText().equals("")) {
          alert.setHeaderText("You forgot to add a Topic");
          alert.setContentText("Ooops, please add text to the Topic box");
          alert.showAndWait();
          return;
        }
        q.setQuestionTopic(topicText.getText());
        
        if(!metaText.getText().equals("")) {
          q.setMetaData(metaText.getText());
        }
        if(correctText.getText().equals("")) {
          alert.setHeaderText("You forgot to add a Correct Answer");
          alert.setContentText("Ooops, please add text to the Correct Answer box");
          alert.showAndWait();
          return;
        }
        ArrayList<Answer> answers = new ArrayList();
        answers.add(new Answer(correctText.getText(),true));
        if(!answerText1.getText().equals("")) {
          answers.add(new Answer(answerText1.getText(),false));
          answerText1.clear();
        }
        if(!answerText2.getText().equals("")) {
          answers.add(new Answer(answerText2.getText(),false));
          answerText2.clear();
        }
        if(!answerText3.getText().equals("")) {
          answers.add(new Answer(answerText3.getText(),false));
          answerText3.clear();
        }
        if(!answerText4.getText().equals("")) {
          answers.add(new Answer(answerText4.getText(),false));
          answerText4.clear();
        }
        if(image.get(0)!=null) {
          q.setImage(image.get(0));
          image.set(0,null);
        }
        topicText.clear();
        correctText.clear();
        metaText.clear();
        questionText.clear();
        
      });
      returnButton.setOnAction(e ->Primary.setScene(sceneList.get(0)));
      
  }
}