package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Question {
  public String questionText;
  public String topic;
  ImageView questionImage;
  List<Answer> answers;
  
  public Question() {
    questionText = "";
    topic = "";
    questionImage = null;
    answers = new ArrayList<Answer>();   
  }
  
  public void setQuestion(String question) {
    if(question != null) {
      this.questionText = question;
    }
  }
  //yo
  public void setQuestionTopic(String topic) {
    if(topic != null) {
      this.topic = topic;
    }
  }
  
  public void setImage(String imageFileName) {
    Image imageSet = new Image(imageFileName);
    this.questionImage = new ImageView(imageSet);
  }
}
