package application;
import java.util.ArrayList;

public class QuestionTable {
	private ArrayList<ArrayList<Question>> table;
	private int numTopics;
	
	public QuestionTable() {
		table = new ArrayList<ArrayList<Question>>();
		numTopics=0;
	}
	
	public void AddQuestion(Question q) {
		for (int i=0; i<table.size();i++) {
			if (table.get(i).get(0).topic.equals(q.topic)) {
				
			}
		}
	}
}


