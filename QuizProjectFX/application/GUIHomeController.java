package application;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

    
public class GUIHomeController extends Scene
{
    private Text count;
    private Button quizButton;
    
    public GUIHomeController(Parent root, Stage Primary, List<Scene> sceneList)
    {
        super(root, 800, 600);
        Primary.setTitle("Home");
        BorderPane parent = (BorderPane) root;
        this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        //Title of the Home screen
        HBox titleBox = new HBox(10);
        Label title = new Label("Quiz Generator!");
        title.setId("title-text");
        titleBox.getChildren().addAll(title);
        titleBox.setAlignment(Pos.CENTER);
        parent.setCenter(titleBox);
        
        parent.setPadding(new Insets(10, 10, 10, 10));

        //This is for the count of the Questions in the Database
        VBox countVBox = new VBox(10);
        HBox countBox = new HBox(10);
        Button countButton = new Button("Update number of Questions");
        Label countText1 = new Label("There are ");
        countText1.setId("answer-text");
        count = new Text(GUIMaster.table.getNumQuestions()+"");
        count.setId("question-text");
        Label countText2 = new Label("questions in the Database ");
        countText2.setId("answer-text");
        countBox.getChildren().addAll(countText1,count,countText2);
        countVBox.setAlignment(Pos.CENTER);
        countVBox.getChildren().addAll(countButton,countBox);
        parent.setRight(countVBox);
        
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        quizButton = new Button("Take Quiz");
        quizButton.setDisable(true);            // Initially have button disabled, set to false when debugging
        quizButton.setPrefSize(150, 100);

        Button loadQuiz = new Button("Load Quiz");
        loadQuiz.setPrefSize(150, 100);
        Button addQuestionButton = new Button("Add Question");
        addQuestionButton.setOnAction(e -> Primary.setScene(sceneList.get(2)));
        addQuestionButton.setPrefSize(150, 100);

        buttons.getChildren().addAll(quizButton, loadQuiz, addQuestionButton);
        parent.setBottom(buttons);
        
        
        // Button Actions
        quizButton.setOnAction(e -> {
          Primary.setScene(sceneList.get(4));
          VBox topicList;
        
          topicList = (((GUITopicSelectController) sceneList.get(4)).updateTopics());        
          ((BorderPane) ((GUITopicSelectController) sceneList.get(4)).root).setTop(topicList);
        });
        
        countButton.setOnAction(e -> {
          updateQuestionCount();
        });
        loadQuiz.setOnAction(e -> {
          FileSystem.loadFile(Primary);
          updateQuestionCount();
        });
    }
    
    /**
     * Updates the the Text object to display the number of questions in the database
     * Also checks and enables the takeQuiz button when there is at least 1 question in database
     * @param count     display text to update
     * @param quizButton
     */
    public void updateQuestionCount()
    {
        int numberOfQuestions = GUIMaster.table.getNumQuestions();
        count.setText(numberOfQuestions + "");
        if (numberOfQuestions > 0)
        {
            quizButton.setDisable(false);               // Enables quiz button 
        } else
        {
            quizButton.setDisable(true);
        }
    }
    
}
