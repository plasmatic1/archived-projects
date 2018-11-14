package ttt.me.tlwv2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	public Square[] board = new Square[9]; 
	
	public static void main(String[] args){
		launch(args);
	}
	
	public Stage stg;
	public Scene mainScene;
	public GridPane gameGridPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stg = primaryStage;
	}

}
