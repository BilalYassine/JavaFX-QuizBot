import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
public class WriteQuestionToJSON
{
	static ArrayList<Answer> answerArrayList = new ArrayList<>();
	static Question question = new Question();
	
	public WriteQuestionToJSON(Question question) {
		WriteQuestionToJSON.question = question;
	}
	
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {
    	JSONArray questionArray = new JSONArray();
    	JSONArray choiceArray = new JSONArray();
    	
    	answerArrayList = (ArrayList<Answer>) question.getAnswers();
    	
	// Loop to iterate answer arraylist for the appropriate answers
    	for(int i = 0; i < answerArrayList.size(); i++) {
    		if(i%2 == 0) {
    			JSONObject choiceArrayAnswer = new JSONObject();
    	    	choiceArrayAnswer.put(answerArrayList.get(i), "");
    	    	choiceArrayAnswer.put(answerArrayList.get(i+1), "");
    	    	choiceArray.add(choiceArrayAnswer);
    		}
    	}
    	
        // Creating JSONObject
        JSONObject questionDetails = new JSONObject();
        questionDetails.put("questionText", question.questionText);
        questionDetails.put("meta-data", question.metaData);
        questionDetails.put("image", question.questionImage);
        questionDetails.put("topic", question.topic);
        questionDetails.put("choiceArray", choiceArray);
         
        JSONObject questionObject = new JSONObject();
        questionArray.add(questionDetails);
        questionObject.put("questionArray", questionArray); 
        
        //Write JSON file
        try (FileWriter file = new FileWriter("C://Users//bilal//eclipse-workspace/cs400_eclipse_p4/src/writetest.json")) {
 
            file.write(questionObject.toJSONString());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
