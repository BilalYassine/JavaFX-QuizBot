package application;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import javafx.application.Application;
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


public class GUIQuizController extends Scene
{

    public Hashtable<String, List<Question>> questionTable = new Hashtable<>();
    public List<Question> quizBank = new ArrayList<>(); //not needed
    public List<Question> topicList = new ArrayList<>();
    public List<Question> qListFinal;
    public int correctAnswers = 0;
    public int questionNumber = 0;

    public GUIQuizController(Parent root, Stage Primary, List<Scene> sceneList, List<Question> questionListFinal)
    {
        super(root, 800, 600);
        this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        qListFinal = questionListFinal;
        Primary.setTitle("Quiz Time");

        // Test Code for question Display
        Question q1 = new Question();
        q1.setQuestionTopic("Science");
        q1.setQuestion("How big is the Sun?");
        Answer a1 = new Answer("Tiny", false);
        Answer a2 = new Answer("Small", false);
        Answer a3 = new Answer("Normal", false);
        Answer a4 = new Answer("Huge", true);
        q1.answers.addAll(Arrays.asList(a1, a2, a3, a4));
        List<Question> qList = new ArrayList<>();
        qList.add(q1);
        questionTable.put(q1.questionText, qList);
        quizBank.add(q1);
        
        Question q2 = new Question();
        q2.setQuestionTopic("Science");
        q2.setQuestion("Is the earth flat?");
        Answer b1 = new Answer("Yes", false);
        Answer b2 = new Answer("No", true);
      
        q2.answers.addAll(Arrays.asList(b1, b2));
        qList.add(q2);
        questionTable.put(q2.questionText, qList);
        quizBank.add(q2);
        // Test Code End

        // Navigation Buttons
        HBox navButtons = new HBox(10);
        navButtons.setPadding(new Insets(0, 0, 10, 0));
        navButtons.setAlignment(Pos.CENTER);

        Button homeButton = new Button("Home");
        navButtons.getChildren().add(homeButton);
        
        Button nextButton = new Button("Next Question");
        navButtons.getChildren().add(nextButton);

        nextButton.setOnAction(e -> { questionNumber++;
        VBox questionDisplay = displayQuestion(Primary, sceneList, questionNumber);
        ((BorderPane) root).setCenter(questionDisplay);});
        
        ((BorderPane) root).setBottom(navButtons);
        homeButton.setOnAction(e -> Primary.setScene(sceneList.get(0)));


        // Question & Answer Layout
        VBox questionDisplay = displayQuestion(Primary, sceneList, questionNumber);

        ((BorderPane) root).setCenter(questionDisplay);
    }


    private VBox displayQuestion(Stage Primary, List<Scene> sceneList, int questionNumber)
    {
      
      //parse through qListFinal for each question
      //if the correct answer is selected, incrememnet variable and go to next question
      
      // make button once, change label.
      // when button clicked, increment variable for question you are at
      
      // check if on last question, if so, go to results page
     System.out.println(correctAnswers);
        if(questionNumber == quizBank.size()) {
          Primary.setScene(sceneList.get(3));
        }
        
        Question current = quizBank.get(questionNumber);
        VBox questionDisplay = new VBox(50);
        questionDisplay.setAlignment(Pos.CENTER);
        
        Label question = new Label(current.questionText);
        question.setId("Question");
        Label isCorrect = new Label();
        isCorrect.setId("Output");
        VBox answers = new VBox(10);
        answers.setAlignment(Pos.CENTER);
        
        
        for(Answer a : current.answers) { 
          
          Button answerButton = new Button(a.answerText);
          answerButton.setId("Answers");   
          
          if(a.correct) {
            answerButton.setOnAction(e -> { 
              correctAnswers++;
              isCorrect.setText("Correct Answer");
              System.out.println("isCorrect");            
              });
          } else {
            answerButton.setOnAction(e -> { 
              isCorrect.setText("Wrong Answer!"); 
              System.out.println("isFalse");
              });
          }
                  
          answers.getChildren().addAll(answerButton); 
        }
        
        answers.getChildren().add(isCorrect);
        
        questionDisplay.getChildren().addAll(question, answers);
        return questionDisplay;
      
    }    
}
