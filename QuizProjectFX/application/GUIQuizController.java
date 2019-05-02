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
    public List<Question> quizBank = new ArrayList<>();
    public List<Question> topicList = new ArrayList<>();
    public int questionNumber = 0;

    public GUIQuizController(Parent root, Stage Primary, List<Scene> sceneList, List<Question> qListFinal)
    {
        super(root, 800, 600);
        this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // Primary.setTitle("Quiz Time");

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
        // Test Code End

        // Navigation Buttons
        HBox navButtons = new HBox(10);
        navButtons.setPadding(new Insets(0, 0, 10, 0));
        navButtons.setAlignment(Pos.CENTER);

        Button homeButton = new Button("Home");
        navButtons.getChildren().add(homeButton);

        ((BorderPane) root).setBottom(navButtons);
        homeButton.setOnAction(e -> Primary.setScene(sceneList.get(0)));


        // Question & Answer Layout
        VBox questionDisplay = displayQuestion();

        ((BorderPane) root).setCenter(questionDisplay);
    }


    private VBox displayQuestion()
    {
        Question current = quizBank.get(questionNumber);
        VBox questionDisplay = new VBox(50);
        questionDisplay.setAlignment(Pos.CENTER);

        Label question = new Label(current.questionText);
        question.setId("question");
        VBox answers = new VBox(10);
        answers.setAlignment(Pos.CENTER);
        for (Answer a : current.answers)
        {
            Button answerButton = new Button(a.answerText);
            answerButton.setId("answers");
            // If a is correct trigger correct else trigger false display
            if (a.correct)
            {
                // replace with proper code instead of sysout
                answerButton.setOnAction(e -> {
                    System.out.println("Correct");
                });
            } else
            {
                answerButton.setOnAction(e -> {
                    System.out.println("Wrong");
                });
            }

            answers.getChildren().add(answerButton);
        }

        questionDisplay.getChildren().addAll(question, answers);

        return questionDisplay;
    }

}
