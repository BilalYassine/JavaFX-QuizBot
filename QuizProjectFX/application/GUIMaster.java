package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIMaster extends Application {

	@Override
	public void start(Stage primaryStage) {
	  try {
        BorderPane root = new BorderPane();
        BorderPane root2 = new BorderPane();
        BorderPane root3 = new BorderPane();
        BorderPane root4 = new BorderPane();
        
        ArrayList<Scene> x = new ArrayList<Scene>();
        GUIHomeController GUIHomeController = new GUIHomeController(root,primaryStage,x);
        x.add(GUIHomeController);
        GUIQuizController GUIQuizController = new GUIQuizController(root2,primaryStage,x);
        x.add(GUIQuizController);
        GUIAddQuestionController GUIAddQuestion = new GUIAddQuestionController(root3,primaryStage,x);
        x.add(GUIAddQuestion);
        GUIQuizEnd GUIQuizEnd = new GUIQuizEnd(root4,primaryStage,x);
        x.add(GUIQuizEnd);

        primaryStage.setScene(GUIHomeController);
        primaryStage.show();
      } catch(Exception e) {
          e.printStackTrace();
      }
  }
	public static void main(String[] args) {
		launch(args);
	}
}
