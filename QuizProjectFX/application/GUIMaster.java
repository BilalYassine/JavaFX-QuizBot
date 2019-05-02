package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

public class GUIMaster extends Application {

	public static QuestionTable table;
	private Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) {
	  try {
	    stage = primaryStage;
        BorderPane root = new BorderPane();
        BorderPane root2 = new BorderPane();
        BorderPane root3 = new BorderPane();
        BorderPane root4 = new BorderPane();
        BorderPane root5 = new BorderPane();
        // GUI List is a list of all the GUI 0 = Home, 1 = Quiz, 2 = Add Question, 3 = Quiz end, 4 = Quiz Topic Selection
        ArrayList<Scene> GUIList = new ArrayList<Scene>();
        GUIHomeController GUIHomeController = new GUIHomeController(root,primaryStage,GUIList,table);
        GUIList.add(GUIHomeController);
        
        List<Question> qListFinal = new ArrayList<Question>();
        
        GUIQuizController GUIQuizController = new GUIQuizController(root2,primaryStage,GUIList,qListFinal);
        GUIList.add(GUIQuizController);

        //GUIQuizController GUIQuizController = new GUIQuizController(root2,primaryStage,GUIList, null);
        //GUIList.add(GUIQuizController);
        
        GUIAddQuestionController GUIAddQuestion = new GUIAddQuestionController(root3,primaryStage,GUIList, table);
        GUIList.add(GUIAddQuestion);
        
        int[] correct = new int[1];
        correct[0] =0;
        GUIQuizEnd GUIQuizEnd = new GUIQuizEnd(root4,primaryStage,GUIList, correct);
        GUIList.add(GUIQuizEnd);
        
        GUITopicSelectController GUITopicSelect = new GUITopicSelectController(root5,primaryStage,GUIList, table, qListFinal);
        GUIList.add(GUITopicSelect);
        
        primaryStage.setScene(GUIHomeController);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() 
        {
            @Override
            public void handle(WindowEvent event)
            {
                exitAlert(event);
            }
        });
        
      } catch(Exception e) {
          e.printStackTrace();
      }
  }
	
	// Called when Program is attempted to be exited, will allow user to 
	// save & exit, cancel and stay in the program, or exit without saving
	private void exitAlert(WindowEvent event) 
	{
	    ButtonType save = new ButtonType("Save and Exit", ButtonBar.ButtonData.OK_DONE);
	    ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
	    ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
	    Alert alert = new Alert(AlertType.CONFIRMATION,"",save,cancel,exit);
	    alert.setHeaderText("You are about to exit");
	    alert.setContentText("Would you like to save your questions before exiting?");
	    
	    alert.showAndWait().ifPresent(response -> {
	        if (response == save) 
	        {
	            FileSystem.saveJSON(stage);
	        } else if(response == cancel)
	        {
	            event.consume();   // consumes the cancel event so program stays running
	        }
	    });
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
