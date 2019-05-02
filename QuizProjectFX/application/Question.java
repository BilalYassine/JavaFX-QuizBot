package application;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class creates is used to store the given
 * questions for the user. A new instance is created
 * everytime a question is read from the JSON file. It also
 * uses the Answer class to store the answers from the given
 * JSON file for that question.
 * @author Bennett Knapek
 *
 */
public class Question {
  // instance fields used for storing question
  boolean isCorrect;
  public String questionText;
  public String topic;
  public String metaData;
  ImageView questionImage;
  List<Answer> answers;
  String[] inputAnswers;
  
  /**
   * Default constructor that takes in an array
   * with all of the possible answers for the given question.
   * @param inputAns
   */
  public Question(String[] inputAns) {
    questionText = "";
    topic = "";
    questionImage = null;
    inputAnswers = inputAns;
    answers = new ArrayList<Answer>();   
  }
  
  //Constructor for testing purposes
  public Question(String q, String t) {
	    questionText = q;
	    topic = t;
	    questionImage = null;
	    answers = new ArrayList<Answer>();   
  }
  
  public Question() {
	    questionText = "";
	    topic = "";
	    questionImage = null;
	    answers = new ArrayList<Answer>();   
  }
  
  /**
   * Setter method for the actual text from the question.
   * @param question: input text from JSON parsing algorithm
   */
  public void setQuestion(String question) {
    if(question != null) { // makes sure question isn't null
      this.questionText = question;
    }
  }
  
  /**
   * Setter method for the meta data that is read from 
   * the JSON file.
   * @param metaData: notes about question
   */
  public void setMetaData(String metaData) {
    this.metaData = metaData;
  }
  //yo
  /**
   * Setter method for the topic of the question that 
   * helps organize the question.
   * @param topic: categorization from JSON file.
   */
  public void setQuestionTopic(String topic) {
    if(topic != null) {
      this.topic = topic;
    }
  }
  
  /**
   * Takes the input filename from the JSON file and creates
   * usable images from it.
   * @param imageFileName: name of file from JSON
   */
  public void setImage(String imageFileName) {
    String path = "images/" + imageFileName;
    Image imageSet = new Image(path); // create image instance
    this.questionImage = new ImageView(imageSet); // converts it for use in JavaFX
  }
  
  /**
   * Method that parses through the array input from the JSON
   * parsing algorithm. Creates and enters all of the Answer
   * instances into the answer ArrayList.
   * @param inputAnswers: array with answers and true/false
   */
  public void setAnswers(String[] inputAnswers)  {
    //parse through the given array
    for(int i = 0; i < inputAnswers.length; i = i + 2) {
      if(inputAnswers[i+1].equals("T")) { // check for true answers
        isCorrect = true;
      } else {
        isCorrect = false;
      }      
      // create and add new instance of answer to arrayList
      answers.add(new Answer(inputAnswers[i], isCorrect));
    }
  }
}