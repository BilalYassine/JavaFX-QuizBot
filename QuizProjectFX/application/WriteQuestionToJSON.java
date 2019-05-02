import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
public class WriteQuestionToJSON
{
	
	static Question question = new Question();
	
	public WriteQuestionToJSON(Question question) {
		WriteQuestionToJSON.question = question;
	}
	
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {
    	JSONArray questionArray = new JSONArray();
    	JSONArray choiceArray = new JSONArray();
    	
    	JSONObject choiceArrayDetail1 = new JSONObject();
    	choiceArrayDetail1.put(question.getAnswers().toString(), question.getAnswers().toString());
    	
    	choiceArray.add(choiceArrayDetail1);
    	
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