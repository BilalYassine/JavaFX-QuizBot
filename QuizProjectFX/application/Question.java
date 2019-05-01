package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Question {
  public String questionText;
  public String metaData;
  public String topic;
  ImageView questionImage;
  List<Answer> answers;
  
  public Question() {
    questionText = "";
    metaData = "";
    topic = "";
    questionImage = null;
    answers = new ArrayList<Answer>();   
  }
  
  public void setQuestion(String question) {
    if(question != null) {
      this.questionText = question;
    }
  }

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
  
  public void setImage(String imageFileName) {
    String path = "images/"+imageFileName;
    Image imageSet = new Image(path);
    this.questionImage = new ImageView(imageSet);
  }
  public void setAnswers(List<Answer> newAnswers) {
    this.answers = newAnswers;
  }
}
