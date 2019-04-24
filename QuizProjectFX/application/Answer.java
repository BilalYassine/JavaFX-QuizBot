package application;

/**
 * This class helps store the possible answers for each
 * question. It takes data read from the JSON and structures
 * that data so its easier to read in other classes.
 * @author Bennett Knapek
 *
 */
public class Answer {
  public String answerText; // actual text of the possible answer
  public boolean correct;   // true if correct answer, false if not
  
  /**
   * Default constructor that takes the input data and
   * assigns it to the instance fields.
   * @param text: actual text of possible answer
   * @param correct: true if correct, false if not
   */
  public Answer(String text, boolean correct) {
    this.answerText = text;
    this.correct = correct;
  }
  
  /**
   * Getter method used get the possible answer of a certain question
   * @return: string containing possible answer text
   */
  public String getAnswerText() {
    return this.answerText;
  }
  
  /**
   * Getter method used to know if the answer is the right one
   * @return: true if its the correct answer, false if not
   */
  public boolean isCorrect() {
    return this.correct;
  }
}
