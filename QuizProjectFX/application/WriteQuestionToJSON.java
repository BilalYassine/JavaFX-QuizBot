package application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteQuestionToJSON
{
    // static ArrayList<Answer> answerArrayList = new ArrayList<>();
    // static Question question = new Question();

    @SuppressWarnings("unchecked")
    public static JSONObject generateJSONObject()
    {
        JSONObject questionObject = new JSONObject();   // Object to write to file
        JSONArray questionArray = new JSONArray();

        for (Question question : GUIMaster.table.getAllQuestions())
        {
        	System.out.println("Saving");
            
            JSONArray choiceArray = new JSONArray();
            ArrayList<Answer> answerArrayList = new ArrayList<>();
            answerArrayList = (ArrayList<Answer>) question.getAnswers();

            // Loop to iterate answer ArrayList for the appropriate answers
            for (int i = 0; i < answerArrayList.size(); i++)
            {
                //if (i % 2 == 0)
                //{
                    JSONObject choiceArrayAnswer = new JSONObject();
                    if (answerArrayList.get(i).correct) 
                    	choiceArrayAnswer.put("isCorrect", "T");
                    else
                    	choiceArrayAnswer.put("isCorrect", "F");
                    choiceArrayAnswer.put("choice", answerArrayList.get(i).answerText);
                    choiceArray.add(choiceArrayAnswer);
                //}
            }

            // Creating JSONObject
            JSONObject questionDetails = new JSONObject();
            questionDetails.put("questionText", question.questionText);
            questionDetails.put("meta-data", question.metaData);
            questionDetails.put("image", question.getImageFileName());
            questionDetails.put("topic", question.topic);
            questionDetails.put("choiceArray", choiceArray);

            
            questionArray.add(questionDetails);
            
        }
        questionObject.put("questionArray", questionArray);

        return questionObject;
    }
}
