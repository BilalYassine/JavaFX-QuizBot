package application;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * This class takes the from multiple other classes and combines
 * all the information into a tailored quiz for the user. It displays all the
 * questions with many options and images that are connected to the question.
 * 
 * @author TATEAM47
 *
 */
public class GUIQuizController extends Scene
{

    public Hashtable<String, List<Question>> questionTable = new Hashtable<>();
    public List<Question> topicList = new ArrayList<>();
    public List<Question> qListFinal;
    public int correctAnswers = 0;
    public int[] correct;
    public int questionNumber = 0; // tracks which question the user is on
    public boolean qAnswered = false; // false if the question hasn't been answered yet
    public boolean qAnsweredCorrect = false; // true if the question was answered correctly
    public VBox questionDisplay;
    public Parent root;
    /**
     * Constructor for the quiz controller scene. Takes in parameters needed for setting
     * up the scene with the correct information. Uses JavaFX to create different constructs
     * to display information and interact with the user. 
     * @param root: the parent scene
     * @param Primary: the main stage that the scenes are placed on
     * @param sceneList: list of all scenes in the program
     * @param questionListFinal: list of the questions in the specified topics and with the correct amount
     * @param correct: keeps track of the number of questions answered correctly
     */
    public GUIQuizController(Parent root, Stage Primary, List<Scene> sceneList, List<Question> questionListFinal, int[] correct)
    {
        super(root, 800, 600); 
        this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        //int[] correct = GUIMaster.correct;
        qListFinal = questionListFinal;
        Primary.setTitle("Quiz Time");
        this.root = root;
        this.correct = correct;
/*
        // Test Code for question Display
        Question q1 = new Question();
        q1.setQuestionTopic("Science");
        q1.setQuestion("How big is the Sun?");
        Answer a1 = new Answer("Tiny", false);
        Answer a2 = new Answer("Small", false);
        Answer a3 = new Answer("Normal", false);
        Answer a4 = new Answer("Huge", true);
        q1.setImage("YoungMeAndKitty.png");
        q1.questionImage.setFitHeight(150);
        q1.questionImage.setPreserveRatio(true);
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
        q2.setImage("defualt.png");
        q2.questionImage.setFitHeight(150);
        q2.questionImage.setPreserveRatio(true);
      
        q2.answers.addAll(Arrays.asList(b1, b2));
        qList.add(q2);
        questionTable.put(q2.questionText, qList);
        quizBank.add(q2);
        // Test Code End
*/
        // Navigation Buttons
        HBox navButtons = new HBox(10); // horizontal box for nav buttons
        navButtons.setPadding(new Insets(0, 0, 10, 0)); // padding for box
        navButtons.setAlignment(Pos.CENTER); // center the box

        Button homeButton = new Button("Home"); // button to get home from quiz
        navButtons.getChildren().add(homeButton); // add button to box
        
        Button nextButton = new Button("Next Question"); // button to get to the next question
        navButtons.getChildren().add(nextButton); // add button to box

        // listener for the nextButton. If called, call methods to get to new question
        nextButton.setOnAction(e -> { questionNumber++;
        VBox questionDisplay = displayQuestion(root, Primary, sceneList, questionNumber, correct);
        ((BorderPane) root).setCenter(questionDisplay);}); 
        
        // listener for home button. If pressed, go back to the home page.
        ((BorderPane) root).setBottom(navButtons);
        homeButton.setOnAction(e -> Primary.setScene(sceneList.get(0)));


        // Question & Answer Layout
        questionDisplay = new VBox();
        ((BorderPane) root).setCenter(questionDisplay);     
    }


    /**
     * This method puts all of the information from each question instance and displays it on 
     * the user interface. 
     * @param root: the parent scene
     * @param Primary: the main stage that the scenes are placed on
     * @param sceneList: list of all scenes in the program
     * @param questionNumber: keeps track of how many questions have been answered
     * @param correct: keeps track of how many questions have been answered correctly.
     * @return: structure with all elements in the boxes
     */
    public VBox displayQuestion(Parent root2, Stage Primary, List<Scene> sceneList, int questionNumber, int[] correct)
    {   
    	
        //Initialize tracking variables
        qAnswered = false; 
        qAnsweredCorrect = false;
        
        //If no questions left, go to the end page scene
        if(questionNumber == qListFinal.size()) {
          Primary.setScene(sceneList.get(3));
          correct[1] = questionNumber;
          ((GUIQuizEnd) sceneList.get(3)).updateScore();
        }
        
        //get the current question from the list
        Question current = qListFinal.get(questionNumber);
        VBox questionDisplay = new VBox(50); // create box for question, answers, info
        questionDisplay.setAlignment(Pos.CENTER); // center the box
        
        // label used to track which question the user is on
        Label qNumAndTot = new Label("(" + (questionNumber+1) + "/" + qListFinal.size() + ")");
        qNumAndTot.setId("Info");
               
        //Label used to display the text of the question
        Label question = new Label(current.questionText);
        question.setId("Question");
        
        //Label used to show if the user answers the question correctly or not
        Label isCorrect = new Label();
        isCorrect.setId("Output");

        // box for all of the possible answers
        VBox answers = new VBox(10);
        answers.setAlignment(Pos.CENTER);
        ImageView questionImage;
        // create object for image to be inserted
        //if(current.questionImage==null) {
        	//questionImage.setImage = "pp.png";
        //}
        //else {
        	questionImage = current.questionImage;
        	questionImage.setId("Photo");
        
        //}
        
        questionImage.setFitHeight(150);
        questionImage.setPreserveRatio(true);
        //Loops through all possible answers and puts them into the box
        for(Answer a : current.answers) { 
              //create button with answer text inside for user interaction
              Button answerButton = new Button(a.answerText);
              answerButton.setId("Answers");   
            
              // listener that checks if the correct answer is selected when a user clicks an answer
              if(a.correct) {
                answerButton.setOnAction(e -> { 
                  correct[0]++; // increment the correct answer tracking variable
                  correct[2]++; // increment the number of questions answered
                  isCorrect.setText("Correct Answer"); // output to user
                  
                  qAnswered = true; // question has been answered
                  qAnsweredCorrect = true; // question has been answered correctly
                  //call method that will display correct answer
                  if(qAnswered == true) {
                    displayCorrectAnswer(root2, questionNumber, correct, qAnsweredCorrect);
                  }
                  });
              } else {
                answerButton.setOnAction(e -> { 
                  isCorrect.setText("Wrong Answer!"); // output to user
                  correct[2]++; // increment the number of questions answered
                  qAnswered = true; // question has been answered
                  qAnsweredCorrect = false; // question has been answered incorrectly
                  //call method that will display correct answer
                  if(qAnswered == true) {
                    displayCorrectAnswer(root2, questionNumber, correct, qAnsweredCorrect);
                  }
                  });
              }
            // add all of the answer buttons to the VBox
            answers.getChildren().addAll(answerButton);
          }
          
        // add all of of the other wanted information to the VBox
        
       
        answers.getChildren().addAll(isCorrect, qNumAndTot, questionImage);
        
        
        
        // add all info connected to the answers and question
        questionDisplay.getChildren().addAll(question, answers);
        return questionDisplay; // return final interface    
    }  
    
  
    /**
     * Method that displays the correct answer and other information when the user clicks
     * one of the answer options.
     * @param root: the parent scene
     * @param questionNumber: keeps track of how many questions have been answered
     * @param correct: keeps track of the number of questions answered correctly
     * @param qAnsweredCorrect: tells whether the user answerd the question correctly
     */
    public void displayCorrectAnswer(Parent root, int questionNumber, int[] correct, boolean qAnsweredCorrect) {
      // All of these elements are the same as displayQuestion----------------------------------
      Question current = qListFinal.get(questionNumber);
      VBox questionDisplay = new VBox(50);
      questionDisplay.setAlignment(Pos.CENTER);
      
      Label question = new Label(current.questionText);
      question.setId("Question");
      Label isCorrect = new Label();
      isCorrect.setId("Output");
      VBox answers = new VBox(10);
      answers.setAlignment(Pos.CENTER);
      
      ImageView questionImage = current.questionImage;
      questionImage.setId("Photo");
      //-----------------------------------------------------------------------------------------
      
      // loop through all of the possible answers to the question
      for(Answer a : current.answers) {
        // create a button for each possible answer
        Button answerButton = new Button(a.answerText);
        answerButton.setId("Answers"); 
        
        // check for the correct answer to display it
        if(a.correct) {
          // Output based on the users answer
          if(qAnsweredCorrect) {
            isCorrect.setText("You chose the correct answer");
          } else {
            isCorrect.setText("You chose the wrong answer. This is the correct one");
          }
          
          // Add all elements to the two boxes for correct display of info
          answers.getChildren().add(answerButton);
          questionDisplay.getChildren().addAll(question, answers, isCorrect , questionImage);
          VBox correctQ = questionDisplay;
          ((BorderPane) root).setCenter(correctQ);
        }
      }
    }
}
