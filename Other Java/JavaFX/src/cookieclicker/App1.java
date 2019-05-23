package cookieclicker;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


// Learning JavaFX :)
// New GD level coming soon!

public class App1 extends Application implements EventHandler<ActionEvent>{
	public Button clickBtn = new Button("Clicker Button");
	public Button buyClickerBtn = new Button("Buy Clicker");
	public Label clicksTxt = new Label("Clicks: 0");
	public Label clickerAmtTxt = new Label("Clickers: 0");
	
	public int delay = 1000;
	public int clickers = 0;
	public int clicks = 0;
	
	public int currentTick = 0;
	
	public Font f = new Font(15);
	public boolean stopped = false;
	
	private Timer loop = new Timer();

	public App1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		clickBtn.setOnAction(this);
		buyClickerBtn.setOnAction(this);
		
		clicksTxt.setFont(f);
		
		pane.setTop(clicksTxt);
		pane.setLeft(clickerAmtTxt);
		pane.setCenter(clickBtn);
		pane.setRight(buyClickerBtn);
		
		Scene ms = new Scene(pane, 300, 300);
		
		stage.setScene(ms);
		stage.setTitle("Redundant Cookie Clicker");
		stage.show();
		
		startLoop();
	}
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource().equals(clickBtn)){
			clicks++;
			
			clicksTxt.setText("Clicks: " + clicks);
		}
		else if(e.getSource().equals(buyClickerBtn)){
			if(clicks >= 50){
				clicks -= 50;
				clicksTxt.setText("Clicks: " + clicks);
				
				clickers++;
				clickerAmtTxt.setText("Clickers: " + clickers);
				
				delay -= 10;
			}
		}
	}
	
	protected void startLoop(){
		loop.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				currentTick++;
				System.out.println(currentTick);
				
				if(currentTick >= delay && clickers >= 1){
					clicks++;
					clicksTxt.setText("Clicks: " + clicks);
					currentTick = 0;
				}
				
				if(stopped){
					loop.cancel();
				}
			}
		}, 0, 10);
	}
	
	@Override
	public void stop(){
		stopped = true;
	}

}
