package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GUITopicSelectController extends Application {

	HashTable<String, Question> questions;
	int numQuestions;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//Create a label for the top of the screen
			Label titleLabel = new Label("Pick a Topic");
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
