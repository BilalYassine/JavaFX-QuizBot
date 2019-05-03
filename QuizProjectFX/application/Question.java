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
  public String metaData;
  public String topic;
  ImageView questionImage;
  public List<Answer> answers;
  String[] inputAnswers;
  String path;
  
  /**
   * Default constructor that takes in an array
   * with all of the possible answers for the given question.
   * @param inputAns
   */
  public Question(String[] inputAns) {
    questionText = "";
    metaData = "";
    topic = "";
    questionImage = null;
    inputAnswers = inputAns;
    answers = new ArrayList<Answer>();
  }
  
  public Question(String q, String t, String m, String i, List<Answer> a) {
	    questionText = q;
	    metaData = m;
	    topic = t;
	    questionImage = null;
	    setImage(i, false);
	    //inputAnswers = inputAns;
	    setAnswers(a);
	    //answers = new ArrayList<Answer>();   
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

  //yo
  /**
   * Setter method for the topic of the question that 
   * helps organize the question.
   * @param topic: categorization from JSON file.
   */
  public void setMetaData(String metaData) {
	    if(metaData != null) {
	      this.metaData = metaData;
	    }
	  }
  
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
  public void setImage(String imageFileName, Boolean jsonRead) {
	if (!jsonRead)
		path = ("images\\" + imageFileName);
	else 
		path = (imageFileName);
    try
    {
        Image imageSet = new Image(path); // create image instance
        this.questionImage = new ImageView(imageSet); // converts it for use in JavaFX
    } catch (Exception e)
    {
        // set default image path if error is thrown
        Image imageSet = new Image("images/default.png");
        this.questionImage = new ImageView(imageSet);
    }
    
  }
  
  public String getImageFileName() {
	  if (path == null) {
		  return "none";
	  }
	  return path;
  }
  
  public void setAnswers(List<Answer> newAnswers) {
    this.answers = newAnswers;
  }
  
  public List<Answer> getAnswers()
  {
      return answers;
  }
}