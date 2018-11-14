package kw.me.tlwv2;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Module {
	
	private VBox inGame;
	private VBox menu;
	
	//Menu
	private TextField name_t;
	private Label key_lp;
	
	//ingame
	private Label clicks_l;
	private Label key_l;
	private Label name_l;
	private Label rank_l;
	
	private ProgressBar progress_b;
	
	//all
	private int clicks;
	private int clickmax;
	private int key;
	
	private String name;
	private String rank;
	
	private String css = "";
	
	public Module(int key, String name) {
		this.key = key;
		this.name = name;
		
		initializeGameboard();
		initializeMenuboard();
	}
	
	private void initializeGameboard(){
		inGame = new VBox(15);
		
		name_l = new Label("Name: " + name);
		name_l.setId("default_label");
		name_l.applyCss();
	}
	
	private void initializeMenuboard(){
		
	}

	public VBox getInGame() {
		return inGame;
	}

	public void setInGame(VBox inGame) {
		this.inGame = inGame;
	}

	public VBox getMenu() {
		return menu;
	}

	public void setMenu(VBox menu) {
		this.menu = menu;
	}

	public TextField getName_t() {
		return name_t;
	}

	public void setName_t(TextField name_t) {
		this.name_t = name_t;
	}

	public Label getKey_lp() {
		return key_lp;
	}

	public void setKey_lp(Label key_lp) {
		this.key_lp = key_lp;
	}

	public Label getClicks_l() {
		return clicks_l;
	}

	public void setClicks_l(Label clicks_l) {
		this.clicks_l = clicks_l;
	}

	public Label getKey_l() {
		return key_l;
	}

	public void setKey_l(Label key_l) {
		this.key_l = key_l;
	}

	public Label getName_l() {
		return name_l;
	}

	public void setName_l(Label name_l) {
		this.name_l = name_l;
	}

	public Label getRank_l() {
		return rank_l;
	}

	public void setRank_l(Label rank_l) {
		this.rank_l = rank_l;
	}

	public ProgressBar getProgress_b() {
		return progress_b;
	}

	public void setProgress_b(ProgressBar progress_b) {
		this.progress_b = progress_b;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getClickmax() {
		return clickmax;
	}

	public void setClickmax(int clickmax) {
		this.clickmax = clickmax;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
