package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
	
public class QuestionTable
{

    // hashTable with Keys as Topics and Values as ArrayList with questions
    private Hashtable<String, List<Question>> table;
    private int numTopics;
    private int numQuestions;

    // Creates a new question table
    public QuestionTable()
    {
        table = new Hashtable<String, List<Question>>();
        numTopics = 0;
        numQuestions = 0;
    }

    // Add a question
    public void addQuestion(Question q)
    {

        if (table.containsKey(q.topic))     // If table contains topic, add question to that ArrayList
        {
            table.get(q.topic).add(q);
        } else                              // If table doesn't contain topic, add new ArrayList with topic and question in it
        {
            List<Question> newTopicList = new ArrayList<>();
            newTopicList.add(q);
            table.put(q.topic, newTopicList);
            numTopics++;
        }
        
        numQuestions++;
    }

    // Get a list of topics
    public List<String> getTopicList()
    {
        return new ArrayList<String>(table.keySet());     // Takes set of keys (Topics) and returns as List
    }

    // Returns a list of questions for the specified topic
    public List<Question> getQuestionsList(String topic)
    {
        return table.get(topic);  
    }
    
    // Gets a list of all Questions in table
    public List<Question> getAllQuestions()
    {
        List<Question> allQuestions = new ArrayList<>();
        Collection<List<Question>> questionsByTopic = table.values();
        for (List<Question> questionList: questionsByTopic)
        {
            allQuestions.addAll(questionList);
        }
        
        return allQuestions;
    }
    
    // get number of topics
    public int getNumTopics()
    {
        return numTopics;
    }
    
    public int getNumQuestions()
    {
        return numQuestions;
    }
    
}


