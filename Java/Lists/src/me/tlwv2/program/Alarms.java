package me.tlwv2.program;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Alarms extends JFrame{
	
	static Toolkit tk = Toolkit.getDefaultToolkit();
	
	static Font titleFont = new Font("Comic Sans MS", 25, 25);
	static Font defFont = new Font("Comic Sans MS", 15, 15);

	public Alarms() {
		int width = (int) (400 * (tk.getScreenSize().getWidth() / 1920));
		int height = (int) (400 * (tk.getScreenSize().getHeight() / 1080));
		
		this.setSize(width, height);
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // temporary (for testing porposes)
		
		this.setVisible(true);
	}
	
	public static void setup(){
		
	}

	public static void main(String[] args) {
		new Alarms();
	}

}
