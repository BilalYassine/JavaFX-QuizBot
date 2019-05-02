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
    public GUIHomeController(Parent root, Stage Primary, List<Scene> sceneList,QuestionTable table)
    {
        super(root, 800, 600);
        Primary.setTitle("Home");
        BorderPane parent = (BorderPane) root;
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
        quizButton.setOnAction(e -> Primary.setScene(sceneList.get(4)));
        quizButton.setPrefSize(100, 100);

        Button loadQuiz = new Button("Load Quiz");
        loadQuiz.setPrefSize(100, 100);
        loadQuiz.setOnAction(e -> {
            FileSystem.loadFile(Primary);
        });
        VBox countVBox = new VBox(10);
        HBox countBox = new HBox(10);
        Button countButton = new Button("Update number of Questions");
        Label countText1 = new Label("There are ");
        countText1.setId("answer-text");
        Text count = new Text(table.getNumQuestions()+"");
        count.setId("question-text");
        countButton.setOnAction(e -> {
          count.setText(table.getNumQuestions()+"");
          System.out.println(table.getNumQuestions());
        });
        Label countText2 = new Label("questions in the Database ");
        countText2.setId("answer-text");
        countBox.getChildren().addAll(countText1,count,countText2);
        countVBox.setAlignment(Pos.CENTER);
        countVBox.getChildren().addAll(countButton,countBox);
        parent.setRight(countVBox);
        
        Button addQuestionButton = new Button("Add Question");
        addQuestionButton.setOnAction(e -> Primary.setScene(sceneList.get(2)));
        addQuestionButton.setPrefSize(100, 100);

        buttons.getChildren().addAll(quizButton, loadQuiz, addQuestionButton);
        parent.setBottom(buttons);
    }
    private int questionNumber(QuestionTable table) {
      int count = 0;
      List<String> topics = table.getTopicList();
      for(int x =0; x<table.getNumTopics();x++) {
        count +=table.getQuestionsList(topics.get(x)).size();
      }
      return count;
    }
}
