package application;

/**
 * Answer Class that stores answer text and whether or not the answer is correct
 */
public class Answer
{
    public String answerText;
    public boolean correct;

    /**
     * Constructor for Answer that sets text and if it is correct
     * @param text      answer Text
     * @param correct   whether this answer is correct or not
     */
    public Answer(String text, boolean correct)
    {
        this.answerText = text;
        this.correct = correct;
    }
}
