package ProgressTest;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressBarTest extends Application {
	public static final String[] RANKS = {"Clicker", "Clicker II", "Clicker III", "Fapper", "Fapper II", "ULTRA FAPPER", "ULTRA FAPPER II", "GOD", "GOD II", "GOD III", "GOD IV", "GOD V", "DIVINE FORCE", "ULTIMATE DIVINE FORCE", "THE ULTIMATE", "II THE ULTIMATE II", "III THE ULTIMATE III", "Cyclic"};
	public static final int MAXTIME = 1500;
	
	private ProgressBar br = new ProgressBar(0.0);
	private ProgressBar time_br = new ProgressBar(1.0);
	private Label clicks_l = new Label("0/0");
	private Label rank_l = new Label("Clicker");
	private Label name_l = new Label("Player");
	private Label level_l = new Label("Level ");
	
	private Label title = new Label("Pick your name");
	private TextArea inputName = new TextArea("Input Name Here");
	private Button start = new Button("Start Game");
	
	private int clicks = 0;
	private String rank;
	private String name;
	private int clickReq = 100;
	
	private int time = MAXTIME;
	private int level = 1;
	
	private VBox game = new VBox(20, time_br, name_l, rank_l, level_l, clicks_l, br);
	private VBox menu = new VBox(20, title, inputName, start);
	
	private Scene game_s = new Scene(game, 300, 600);
	private Scene menu_s = new Scene(menu, 300, 600);
	
	private Node[] nodes = {br, time_br, clicks_l, rank_l, name_l, level_l, title, inputName, start};
	
	@Override
	public void start(Stage stage) throws Exception {
		game_s.getStylesheets().add(getClass().getResource("pbt.css").toExternalForm());
		menu_s.getStylesheets().add(getClass().getResource("pbt.css").toExternalForm());
		
		for(Node n : nodes){
			n.getStyleClass().add("deflabel");
		}
		
		inputName.setPrefWidth(600);
		inputName.setPrefHeight(25);
		inputName.setWrapText(false);
		
		inputName.setOnMouseClicked(e -> {
			inputName.setText("");
		});
		
		start.setOnMousePressed(e -> {
			setLabelsStr();
			setLabelsInt();
			
			name = inputName.getText();
			
			name_l.setText(name);
			
			stage.setScene(game_s);
			game_s.setOnKeyPressed(evt -> {
				clicks++;
				
				setLabelsStr();
				setLabelsInt();
			});
			
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							time--;
							
							System.out.println("Time left: " + time + "/" + MAXTIME);
							
							time_br.setProgress((double) time / (double) MAXTIME);
							
							System.out.println(time_br.getProgress());
							
							if(clicks >= clickReq){
								clicks_l.setId("clicks_complete");
								clicks_l.setText(clickReq + "/" + clickReq);
							}
							else{
								setLabelsInt();
								clicks_l.setId("clicks_normal");
							}
							
							if(time <= 0){
								if(clicks >= clickReq){
									time = MAXTIME;
									clicks = 0;
									level++;
									rank = RANKS[(level - 1)];
									
								clickReq += 100;
									
									setLabelsStr();
									setLabelsInt();
								}
							}
						}
					});
				}
			}, 0, 10);
		});
		
		rank = RANKS[0];
		name = inputName.getText();
		
		setLabelsStr();
		setLabelsInt();
		
		stage.setScene(menu_s);
		stage.setTitle("Clicker Game");
		stage.show();
	}
	
	private void setLabelsStr(){
		name_l.setText(name);
		rank_l.setText(rank);
	}
	
	private void setLabelsInt(){
		level_l.setText("Level " + level);
		clicks_l.setText(clicks + "/" + clickReq);
		br.setProgress((double) clicks / (double) clickReq);
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
