package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;

public class FileManager {
	
	private QuestionTable quizTable;
	
	public FileManager() {
		quizTable = new QuestionTable(); // A hash table filled with all of the questions
	}
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void parseJSON(File jsonFilepath) throws FileNotFoundException, IOException, ParseException {
    	 // parsing file
        Object obj = new JSONParser().parse(new FileReader(jsonFilepath));

        // typecasting obj to JSONObject 
        JSONObject jo = (JSONObject) obj;
        
        // getting packages 
        JSONArray ja = (JSONArray) jo.get("questionArray"); 
          
        // iterating packages 
        Iterator itr2 = ja.iterator();
        
        int questionCounter = 0;
        ArrayList<String> questionsText = new ArrayList<String>();
        ArrayList<String> topics = new ArrayList<String>();
        ArrayList<Question> questions = new ArrayList<Question>();
          
        while (itr2.hasNext())  
        { 
            Iterator itr1 = ((Map) itr2.next()).entrySet().iterator(); 
            Map.Entry pair = (Entry) itr1.next(); 
            questionCounter++;
            Question question = new Question();
//            System.out.println("Question " + questionCounter + ": ");
            if(pair.getKey().toString().contentEquals("image")) {
//            	System.out.println("  The image is : " + pair.getValue());
            	question.setImage(pair.getValue().toString());
            }
            while (itr1.hasNext()) { 
                Map.Entry pair2 = (Entry) itr1.next(); 
                if(pair2.getKey().toString().contentEquals("meta-data")) {
//                	System.out.println("  The meta data is : " + pair2.getValue());
                }
                if(pair2.getKey().toString().contentEquals("questionText")) {
//                	System.out.println("  The question text is : " + pair2.getValue());
                	String questionText = pair2.getValue().toString();
                	questionsText.add(questionText);
//                    System.out.println("Current Questions: " + questionsText);
//                    System.out.println("Current Topics: " + topics);
                    question.setQuestion(questionText);
                    quizTable.AddQuestion(question);
                }
                if(pair2.getKey().toString().contentEquals("topic")) {
//                	System.out.println("  The topic is : " + pair2.getValue());
                	String topicText = pair2.getValue().toString();
                	topics.add(topicText);
                	question.setQuestionTopic(topicText);
                }
                if(pair2.getKey().toString().contentEquals("choiceArray")) {
                	String[] answerArray = pair2.getValue().toString().replace("[", "").replace("\"", "").replace("{",  "").replace("]",  "").replace("$", "").replace("}", "").split(",");
//                	System.out.println("  The array of answers is : ");
                	ArrayList<Answer> answerList = new ArrayList<Answer>();
                	for(int i = 0; i < answerArray.length; i++) {
                		String newString = new String();
                		if(i%2 == 0) {
                			newString = answerArray[i] + " " + answerArray[i+1];
                			if(answerArray[i+1].contains("T")) {
                				Answer answer = new Answer(answerArray[i], true);
                				answerList.add(answer);
                			}
                			else {
                				Answer answer = new Answer(answerArray[i], false);
                				answerList.add(answer);
                			}
                		}
//                		System.out.println("          " + newString);
                	}
            		question.setAnswers(answerList);
                }
                String[] stringArray = pair2.getValue().toString().replace("[", "").replace("\"",  "").replace("]",  "").split(",");
                for(String vertex2 : stringArray) {
                }
            } 
        } 
    }
}
