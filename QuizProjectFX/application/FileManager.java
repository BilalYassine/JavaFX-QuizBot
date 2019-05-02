package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;

final class FileManager {
    
    /**
     * Takes in a file path for a json file and builds the appropriate Questions, Answers, and QuestionTable
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public static void parseJSON(File jsonFilepath) throws FileNotFoundException, IOException, ParseException {
    	 // parsing file
        Object obj = new JSONParser().parse(new FileReader(jsonFilepath));

        // Cast Object to JSONObject 
        JSONObject jo = (JSONObject) obj;
        
        // get question array
        JSONArray ja = (JSONArray) jo.get("questionArray"); 
        
        // Iterate through each question in JSONArray ja
        for (Object q : ja)
        {
            JSONObject jQ = (JSONObject) q;
            Question question = new Question();
            List<Answer> answerList = new ArrayList<>();
            
            // Set Question Data
            question.setMetaData((String) jQ.get("meta-data"));
            question.setQuestion((String) jQ.get("questionText"));
            question.setQuestionTopic((String) jQ.get("topic"));
            question.setImage((String) jQ.get("meta-data"));
            
            JSONArray choices = (JSONArray) jQ.get("choiceArray");
            
            // Iterate through each answer in question
            for (Object choice : choices)
            {
                JSONObject jChoice = (JSONObject) choice;
                
                //if "T" set true, else false
                Boolean aCorrect = (jChoice.get("isCorrect")).equals("T") ? true : false;
                String aText = (String) jChoice.get("choice");
                answerList.add( new Answer(aText, aCorrect) );
            }
            
            // Add compiled question to quizTable
            question.setAnswers(answerList);
            GUIMaster.table.addQuestion(question);
            System.out.println(GUIMaster.table.getNumQuestions());
            
        }
    }
}
