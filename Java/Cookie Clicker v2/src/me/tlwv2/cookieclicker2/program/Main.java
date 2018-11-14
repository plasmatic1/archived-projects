package me.tlwv2.cookieclicker2.program;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	private Stage stage;
	
	private Scene gameScene;
	
	private HBox countDisplays;
	private HBox bottomBtns;
	
	private VBox display;
	
	private Label clicksDisplay = new Label("Clicks: 0");
	private Label clickerDisplay = new Label("Clickers: 0");
	
	private Button clickBtn;
	private Button clickerBtn;
	private Button backBtn;
	
	private Font mainfont = new Font("comicsans", 20);
	private Font largeFont = new Font("comicsans", 35);
	
	//---------------------------------- Game Logic
	
	private int clicks;
	private int clickers;
	private int delay = 1010;
	private int clicksPerRnd = 1;
	private int clickerCost = 50;
	
	private int currentTick = 0;
	private boolean hasClickers = false;
	
	static final int PADDING = 10;
	
	private Timer gameLoop = new Timer();
	
	public Main(){
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		
		
		//Init buttons ---------------------
		clickBtn = new Button("Click Me!");
		clickBtn.setFont(largeFont);
		clickBtn.setTooltip(new Tooltip("How you get clicks"));
		clickBtn.setOnMousePressed(e -> {
			clicks++;
			clicksDisplay.setText("Clicks: " + clicks);
		});
		
		clickerBtn = new Button("Buy a clicker (50 clicks)");
		clickerBtn.setFont(mainfont);
		clickerBtn.setTooltip(new Tooltip("Buys you a clicker for " + clickerCost + " clicks"));
		clickerBtn.setOnMousePressed(e -> {
			if(clicks >= clickerCost){
				clicks -= clickerCost;
				hasClickers = true;
				
				if(delay > 0){
					clickers++;
					delay = 1010 - (clickers * 10);
					clickerCost = 50 + (clickers * 5);
				}
				else{
					clicksPerRnd++;
					clickerCost = 50 + (clickers * 5);
				}
				
				clicksDisplay.setText("Clicks: " + clicks);
				clickerDisplay.setText("Clickers: " + clickers);
				clickerBtn.setText("Buy a clicker (" + clickerCost + " clicks)");
			}
		});
		
		backBtn = new Button("Go Back [WIP]");
		backBtn.setFont(mainfont);
		backBtn.setTooltip(new Tooltip("Saves and Closes the game"));
		backBtn.setOnMousePressed(e -> {
			
		});
		
		//------------------------------------------------ Labels
		
		clicksDisplay.setFont(mainfont);
		
		clickerDisplay.setFont(mainfont);
		
		//------------------------- Layouts
		
		countDisplays = new HBox(PADDING, clicksDisplay, clickerDisplay);
		countDisplays.setAlignment(Pos.CENTER);
		
		bottomBtns = new HBox(PADDING, clickerBtn, backBtn);
		bottomBtns.setAlignment(Pos.CENTER);
		
		display = new VBox(PADDING, countDisplays, clickBtn, bottomBtns);
		display.setAlignment(Pos.CENTER);
		display.setPrefSize(400, 200);
		
		//--------------------------- Scene and stage
		
		gameScene = new Scene(display, 400, 200);
		
		stage.setScene(gameScene);
		stage.setTitle("RCookie Clicker v2");
		stage.setResizable(false);
		stage.show();
		
		initLogic();
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void initLogic(){
		gameLoop.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				currentTick++;
				
				if(currentTick >= delay && hasClickers){
					clicks += clicksPerRnd;
					changeTextOOT("Clicks: " + clicks, clicksDisplay);
					currentTick = 0;
				}
			}
		}, 0, 10);
	}
	
	public void changeTextOOT(String text, Label label){
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				label.setText(text);
			}
		});
	}
}
