package application;

import java.util.ArrayList;
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
        // GUI List is a list of all the GUI 0 = Home, 1 = Quiz, 2 = Add Quesiton, 3 = Quiz end, 4 = Quiz Topic Selection
        ArrayList<Scene> GUIList = new ArrayList<Scene>();
        GUIHomeController GUIHomeController = new GUIHomeController(root,primaryStage,GUIList,table);
        GUIList.add(GUIHomeController);
        
        GUIQuizController GUIQuizController = new GUIQuizController(root2,primaryStage,GUIList);
        GUIList.add(GUIQuizController);
        
        GUIAddQuestionController GUIAddQuestion = new GUIAddQuestionController(root3,primaryStage,GUIList, table);
        GUIList.add(GUIAddQuestion);
        
        int[] correct = new int[1];
        correct[0] =0;
        GUIQuizEnd GUIQuizEnd = new GUIQuizEnd(root4,primaryStage,GUIList, correct);
        GUIList.add(GUIQuizEnd);
        
        GUITopicSelectController GUITopicSelect = new GUITopicSelectController(root5,primaryStage,GUIList);
        GUIList.add(GUITopicSelect);
        
        primaryStage.setScene(GUIHomeController);
        primaryStage.show();
      } catch(Exception e) {
          e.printStackTrace();
      }
  }
	public void stop() {
	  
	}
	public static void main(String[] args) {
		table = new QuestionTable();
		
		launch(args);
	}
}
