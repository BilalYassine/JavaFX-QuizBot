package application;

public class Answer {
  public String answerText;
  public boolean correct;
  
  public Answer(String text, boolean correct) {
    this.answerText = text;
    this.correct = correct;
  }
  
  public String getAnswerText() {
    return this.answerText;
  }
  
  public boolean isCorrect() {
    return this.correct;
  }
}
