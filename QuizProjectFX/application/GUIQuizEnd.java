package application;


    
import java.text.DecimalFormat;
import java.util.List;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GUIQuizEnd extends Scene {
	public Label correct;
	public Label total;
	public Label answered;
	public Label percentage;
	int[] numCorrect;
    public GUIQuizEnd(Parent root,Stage Primary, List<Scene> sceneList,int[] numCorrect) {
      super(root,800,600);
      this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      
      this.numCorrect = numCorrect;
      
      Primary.setTitle("Results");
      BorderPane parent = (BorderPane)root;
      HBox titleBox = new HBox(10);
      Label title = new Label("End of Quiz");
      title.setId("title-text");
      titleBox.getChildren().addAll(title);
      titleBox.setAlignment(Pos.CENTER);
      parent.setTop(titleBox);
      
      VBox center = new VBox(10);
      
      HBox correctBox = new HBox(10);
      Label text = new Label("You got ");
      text.setId("subtitle-text");
      correct = new Label(numCorrect[0] + "");
      correct.setId("title-text");
      total = new Label(numCorrect[1] + "");
      total.setId("title-text");
      Label text2 = new Label(" out of ");
      text2.setId("subtitle-text");
      Label text3 = new Label(" correct!");
      text3.setId("subtitle-text");
      correctBox.getChildren().addAll(text,correct,text2,total,text3);
      correctBox.setAlignment(Pos.CENTER);
      
      HBox answeredBox = new HBox(10);
      Label answeredText1 = new Label("You answered ");
      answeredText1.setId("subtitle-text");
      answered = new Label(numCorrect[2] + "");
      answered.setId("title-text");
      Label answeredText2 = new Label(" Questions");
      answeredText2.setId("subtitle-text");
      answeredBox.getChildren().addAll(answeredText1,answered,answeredText2);
      answeredBox.setAlignment(Pos.CENTER);
      
      HBox percentBox = new HBox(10);
      Label percentText1 = new Label("That is ");
      percentText1.setId("subtitle-text");
      percentage = new Label(0+"");
      percentage.setId("title-text");
      Label percentText2 = new Label("% Percent");
      percentText2.setId("subtitle-text");
      percentBox.getChildren().addAll(percentText1,percentage,percentText2);
      percentBox.setAlignment(Pos.CENTER);
      
      center.getChildren().addAll(correctBox,answeredBox,percentBox);
      center.setAlignment(Pos.CENTER);
      parent.setCenter(center);
      
      Button homeButton = new Button("Home");
      homeButton.setPrefSize(100, 100);
      parent.setBottom(homeButton);
      homeButton.setOnAction(e ->Primary.setScene(sceneList.get(0)));
  }
    
    public void updateScore() {
    	correct.setText(numCorrect[0] + "");
    	total.setText(numCorrect[1] + "");
    	answered.setText(numCorrect[2] +"");
    	numCorrect[2] = 0;
    	double percent =((double)numCorrect[0])/numCorrect[1] * 100;
    	DecimalFormat df = new DecimalFormat("###.##");
    	percentage.setText(df.format(percent));
    }
    
}
