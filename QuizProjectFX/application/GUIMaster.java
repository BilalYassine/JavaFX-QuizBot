package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIMaster extends Application {

	static QuestionTable table;
	
	@Override
	public void start(Stage primaryStage) {
	  try {
        BorderPane root = new BorderPane();
        BorderPane root2 = new BorderPane();
        BorderPane root3 = new BorderPane();
        BorderPane root4 = new BorderPane();
        BorderPane root5 = new BorderPane();
        
        ArrayList<Scene> x = new ArrayList<Scene>();
        
        List<Question> qListFinal = new ArrayList<Question>();
        
        GUIHomeController GUIHomeController = new GUIHomeController(root,primaryStage,x,table);
        x.add(GUIHomeController);
        GUIQuizController GUIQuizController = new GUIQuizController(root2,primaryStage,x,qListFinal);
        x.add(GUIQuizController);
        GUIAddQuestionController GUIAddQuestion = new GUIAddQuestionController(root3,primaryStage,x);
        x.add(GUIAddQuestion);
        GUIQuizEnd GUIQuizEnd = new GUIQuizEnd(root4,primaryStage,x);
        x.add(GUIQuizEnd);
        GUITopicSelectController GUITopicSelect = new GUITopicSelectController(root5,primaryStage,x,table,qListFinal);
        x.add(GUITopicSelect);
        
        primaryStage.setScene(GUIHomeController);
        primaryStage.show();
      } catch(Exception e) {
          e.printStackTrace();
      }
  }
	public static void main(String[] args) {
		table = new QuestionTable();
		Question q = new Question("Yeller", "Yoit");
		table.addQuestion(q);
		table.addQuestion(new Question("Yee", "Yoit"));
		table.addQuestion(new Question("Yo", "Yay"));
		table.addQuestion(new Question("Yo", "Dope"));
		
		launch(args);
	}
}
