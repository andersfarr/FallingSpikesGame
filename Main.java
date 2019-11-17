package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Main extends Application{
	public static void main(String [] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		try{
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("gamePane.fxml"));
			Scene scene = new Scene(root, 640, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game");
			primaryStage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}