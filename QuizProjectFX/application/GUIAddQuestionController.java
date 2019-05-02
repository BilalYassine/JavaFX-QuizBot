package application;


    
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GUIAddQuestionController extends Scene {
    public GUIAddQuestionController(Parent root,Stage Primary, List<Scene> sceneList, QuestionTable table) {
      super(root,800,600);
      Primary.setTitle("Add Question");
      BorderPane parent = (BorderPane)root;
      this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      parent.setPadding(new Insets(10, 10, 10, 10));
      final FileChooser fileChooser = new FileChooser();
      fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Images", "jpg", "png", "gif"));
      ArrayList<String> image = new ArrayList<String>();//image is a ArrayList so it can be accessed inside the 
                                                       // addImage button action
      image.add(null);
      Button addImage = new Button("Add Image");
      // This takes in a file from the file chooser
      addImage.setOnAction(e -> {
        File file = fileChooser.showOpenDialog(Primary);
        if (file != null) {
            image.set(0, file.getAbsolutePath());
        }
      });
      parent.setRight(addImage);
      
      VBox choices = new VBox(10);

      Label trueLabel = new Label("Is the answer True?");
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
      meta.getChildren().addAll(metaLabel,metaText,trueLabel);

      
      Label answerLabel1 = new Label("Answer 1");
      TextField answerText1 = new TextField();
      ToggleButton answerButton1 = new ToggleButton();
      HBox answer1 = new HBox(8);
      answer1.getChildren().addAll(answerLabel1,answerText1, answerButton1);
      
      Label answerLabel2 = new Label("Answer 2");
      TextField answerText2 = new TextField();
      ToggleButton answerButton2 = new ToggleButton();
      HBox answer2 = new HBox(8);
      answer2.getChildren().addAll(answerLabel2,answerText2,answerButton2);
      
      Label answerLabel3 = new Label("Answer 3");
      TextField answerText3 = new TextField();      
      ToggleButton answerButton3 = new ToggleButton();
      HBox answer3 = new HBox(8);
      answer3.getChildren().addAll(answerLabel3,answerText3,answerButton3);
      
      Label answerLabel4 = new Label("Answer 4");
      TextField answerText4 = new TextField();
      ToggleButton answerButton4 = new ToggleButton();
      HBox answer4 = new HBox(8);
      answer4.getChildren().addAll(answerLabel4,answerText4,answerButton4);
      
      
      Label answerLabel5 = new Label("Answer 5");
      TextField answerText5 = new TextField();
      ToggleButton answerButton5 = new ToggleButton();
      HBox answer5 = new HBox(8);
      answer5.getChildren().addAll(answerLabel5,answerText5,answerButton5);
      
      HBox titleBox = new HBox(10);
      Label title = new Label("Add a Question");
      title.setId("title-text");
      titleBox.setAlignment(Pos.CENTER);
      titleBox.getChildren().addAll(title);
      parent.setTop(titleBox);
      
      choices.getChildren().addAll(question,topics,meta,answer1,answer2,answer3,answer4,answer5);
      parent.setCenter(choices);
      
      
      HBox bottom = new HBox(8);
      Button returnButton = new Button("Return");
      Button saveButton = new Button("Save Question");
      bottom.getChildren().addAll(returnButton,saveButton);
      
      parent.setBottom(bottom);
      
      returnButton.setOnAction(e ->Primary.setScene(sceneList.get(0)));//Returns to the home screen
      /** When the save Button is pressed it will take the various inputs from this screen and createes
       * a question from it.
       * If there is no question, no topic, no answers, or no correct answers a popup will display with the error
       * and no question will be made.
       * If making the question is successful then all fields are cleared. 
       */
      saveButton.setOnAction(e -> {
        Question q = new Question();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        if(questionText.getText().equals("")) {//checks whether the text of the question was entered
          alert.setHeaderText("You forgot to add a Question");
          alert.setContentText("Ooops, please add text to the question box");
          alert.showAndWait();
          return;
          
        }
        q.setQuestion(questionText.getText());
        if(topicText.getText().equals("")) {//checks whether the topic of the question was entered
          alert.setHeaderText("You forgot to add a Topic");
          alert.setContentText("Ooops, please add text to the Topic box");
          alert.showAndWait();
          return;
        }
        q.setQuestionTopic(topicText.getText());
        int correct = 0;
        if(!metaText.getText().equals("")) {//If the metadata is not empty it will add it to the question
          q.setMetaData(metaText.getText());
        }
        ArrayList<Answer> answers = new ArrayList();
        if(!answerText1.getText().equals("")) {//If the answer is not empty it will add it to the question
          if(answerButton1.isSelected()) {//If the question button is selected that answer is correct
            answers.add(new Answer(answerText1.getText(),true));
            correct++;
          }
          else
            answers.add(new Answer(answerText1.getText(),false));
        }
        if(!answerText2.getText().equals("")) {
          if(answerButton2.isSelected()) {
            answers.add(new Answer(answerText2.getText(),true));
            correct++;
          }
          else
            answers.add(new Answer(answerText2.getText(),false));
        }
        if(!answerText3.getText().equals("")) {
          if(answerButton3.isSelected()) {
            answers.add(new Answer(answerText3.getText(),true));
            correct++;
          }
          else
            answers.add(new Answer(answerText3.getText(),false));
        }
        if(!answerText4.getText().equals("")) {
          if(answerButton4.isSelected()) {
            answers.add(new Answer(answerText4.getText(),true));
            correct++;
          }
          else
            answers.add(new Answer(answerText4.getText(),false));
        }
        if(!answerText5.getText().equals("")) {
          if(answerButton5.isSelected()) {
            answers.add(new Answer(answerText5.getText(),true));
            correct++;
          }
          else
            answers.add(new Answer(answerText5.getText(),false));
        }
        if(image.get(0)!=null) {
          q.setImage(image.get(0));
          image.set(0,null);
        }
        if(answers.size()<1) {//Checks to make sure at lease one answer is entered
          alert.setHeaderText("You forgot to add an Answer");
          alert.setContentText("Ooops, please add at least one Answer");
          alert.showAndWait();
          return;
        }
        if(correct<1) {//Checks to make sure at lease one of the answers is correct
          alert.setHeaderText("You forgot to add a correct Answer");
          alert.setContentText("Ooops, please add at least one correct Answer");
          alert.showAndWait();
          return;
        }
        topicText.clear();
        metaText.clear();
        questionText.clear();
        answerText1.clear();
        answerText2.clear();
        answerText3.clear();
        answerText4.clear();
        answerText5.clear();
        table.AddQuestion(q);
      });    
  }
}

