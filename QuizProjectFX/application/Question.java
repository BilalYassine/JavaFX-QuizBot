package application;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Each question has an instance of this class
 * to better organize the data that comes along with
 * a question.
 * @author Bennett Knapek
 *
 */
public class Question {
  public String questionText; // actual question
  public String topic;        // category for the question
  public ImageView questionImage; // image that can be connected to the quesiton
  public List<Answer> answers; // list of all the possible answers for the question
  
  /**
   * Default construction that initializes each variable
   */
  public Question() {
    questionText = "";
    topic = "";
    questionImage = null;
    answers = new ArrayList<Answer>();
  }
  
  /**
   * Sets the instance field to the question text read from JSON file
   * @param question: actual question text
   */
  public void setQuestion(String question) {
    if(question != null) {
      this.questionText = question;
    }  
  }
  
  /**
   * Sets the instance field to the topic read from JSON file
   * @param topic
   */
  public void setQuestionTopic(String topic) {
    if(topic != null) {
      this.topic = topic;
    }  
  }
  
  /**
   * Takes the name of the file and creates a usable ImageView object out of it
   * @param imageFileName
   */
  public void setImage(String imageFileName) {
    if(imageFileName != null) {
      Image image = new Image(imageFileName); 
      this.questionImage = new ImageView(image);
    }   
  }
  
  /**
   * Each answer read from JSON file will call this method. The method
   * creates a new answer object and then adds that object to the 
   * Array List of answers.
   * @param answer: text of a possible answer
   * @param correct: true if its the right answer, false if its not
   */
  public void setAnswers(String answer, boolean correct) {
    if(answer != null) {
      answers.add(new Answer(answer, correct));
    }
    
  }
}
