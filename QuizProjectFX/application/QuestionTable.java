package application;
import java.util.ArrayList;
import java.util.List;

public class QuestionTable {
	private ArrayList<ArrayList<Question>> table;
	private int numTopics;
	
	//Creates a new question table
	public QuestionTable() {
		table = new ArrayList<ArrayList<Question>>();
		numTopics=0;
	}
	
	//Add a questions
	public void AddQuestion(Question q) {
	    
		for (int i=0; i<table.size();i++) {
			if (table.get(i).get(0).topic.equals(q.topic)) {
				table.get(i).add(q);
				return;
			}
		}
		table.add(new ArrayList<Question>());
		table.get(table.size()-1).add(q);     //size -1 
	}
	
	//Get a list of topics
	public List<String> getTopicList() {
		ArrayList<String> topicList = new ArrayList<String>();
		
		for (int i=0; i<table.size();i++) {
			topicList.add(table.get(i).get(0).topic);
		}
		return topicList;
	}
	
	//Returns a list of questions for the specified topic
	public List<Question> getQuestionsList(String t) {
		ArrayList<Question> questionsList = new ArrayList<Question>();
		for (int i=0; i<table.size(); i++) {
			if (table.get(i).get(0).topic.equals(t)) {
				for (int j=0; j<table.get(i).size(); j++) {
					questionsList.add(table.get(i).get(j));
				}
			}
		}
		return questionsList;
	}
}


