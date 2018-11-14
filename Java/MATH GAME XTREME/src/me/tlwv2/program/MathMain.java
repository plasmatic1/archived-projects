package me.tlwv2.program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MathMain extends JFrame{
	public static int num1 = 0;
	public static int num2 = 0;
	public static int answer = 0;
	public static int max = 25;
	
	public static int timeLeft = 1000;
	public static int maxTime;
	public static int difficulty = 0;
	
	public static int userAnswer = 0;
	public static int testvar = 0;
	
	public static int score = 0;
	
	public static Font h1 = new Font("Pusab", 40, 30);
	public static Font defT = new Font("Pusab", 20, 20);
	
	public static boolean hasLost = false;
	public static boolean started = false;
	public static boolean isCorrect = false;
	public static boolean first = true;
	
	public static String operation = Constants.OP_PLUS;
	public static String[] difficulties = {"Easy", "Normal", "Hard", "3XTR3M3"};
	
	public static JPanel top = new JPanel();
	public static JPanel middle = new JPanel();
	public static JPanel bottom = new JPanel();
	public static JPanel beforeStart = new JPanel();
	public static JPanel beforeStartOpt = new JPanel();
	public static JPanel lost = new JPanel();
	public static JPanel lostButt = new JPanel();
	
	public static JButton checkAnswer = new JButton("Submit Answer");
	public static JButton start = new JButton("Start Math Game");
	public static JButton restart = new JButton("Go Back");
	
	public static JLabel timeLeftTxt = new JLabel("Time Left: " + timeLeft);
	public static JLabel title = new JLabel("XTreme Math Game");
	public static JLabel operationTxt = new JLabel();
	public static JLabel scoreTxt = new JLabel("Score: " + score);
	public static JLabel isCorrectTxt = new JLabel("Undetermined");
	public static JLabel death = new JLabel("Your Score Was: ");
	
	public static JTextField enterAnswerBox = new JTextField(4);
	
	public static JComboBox<String> difficultySelector = new JComboBox<String>(difficulties);
	
	public static int[] parts = generateQuestion(max, Constants.OP_DEF_OPERATIONS);
	
	public MathMain(){
		this.setTitle("Extreme Math Game");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setVisible(true);
		
		setup();
		
		this.add(beforeStart, BorderLayout.NORTH);
		this.add(beforeStartOpt);
		
		new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				if(started){
					timeLeft--;
					
					timeLeftTxt.setText("Time Left: " + (double) (timeLeft / 100) + " Seconds");
					
					if(first){
						
						num1 = parts[0];
						num2 = parts[1];
						answer = parts[2];
						
						operationTxt.setText(num1 + " " + fromOperation(parts[3]) + " " + num2);
						
						first = false;
					}
				}
				
				if(timeLeft <= 0 && started){
					hasLost = true;
					started = false;
				}
				
				if(hasLost){
					killPlayer();
				}
			}
		}, 1000, 10);
	}
	
	public static void main(String[] args){
		new MathMain();
	}
	
	public void setup(){
		title.setFont(h1);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		beforeStart.add(title, BorderLayout.NORTH);
		
		start.setFont(defT);
		start.addActionListener(new ButtonListener());
		start.setVisible(true);
		
		beforeStartOpt.add(start, BorderLayout.AFTER_LAST_LINE);
		
		difficultySelector.setFont(defT);
		difficultySelector.setEditable(false);
		
		beforeStartOpt.add(difficultySelector);
		
		//When Started
		
		timeLeftTxt.setFont(defT);
		timeLeftTxt.setHorizontalAlignment(SwingConstants.CENTER);
		timeLeftTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		top.add(timeLeftTxt);
		
		scoreTxt.setFont(defT);
		scoreTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		top.add(scoreTxt);
		
		operationTxt.setFont(defT);
		operationTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		middle.add(operationTxt);
		
		isCorrectTxt.setFont(defT);
		isCorrectTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		isCorrectTxt.setOpaque(true);
		
		middle.add(isCorrectTxt);
		
		enterAnswerBox.setEditable(true);
		enterAnswerBox.setFont(defT);
		enterAnswerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		enterAnswerBox.setMinimumSize(enterAnswerBox.getMaximumSize());
		
		bottom.add(enterAnswerBox);
		
		checkAnswer.setFont(defT);
		checkAnswer.addActionListener(new ButtonListener());
		
		bottom.add(checkAnswer);
		
		//----------------------------------------------
		
		death.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		death.setFont(new Font("Comic Sans MS", 20, 20));
		
		lost.add(death);
		
		restart.setFont(defT);
		restart.addActionListener(new ButtonListener());
		
		lostButt.add(restart);
		
	}
	
	public static int[] generateQuestion(int max, String[] operations){
		int index = (int) (Math.random() * operations.length);
		
		String operation = operations[index];
		int num1 = (int) (Math.random() * max);
		int num2 = (int) (Math.random() * max);
		
		if(num2 == 0) num2 = 1;
		
		int answer = 0;
		
		if(operation.equalsIgnoreCase("+")){
			answer = num1 + num2;
		}
		else if(operation.equalsIgnoreCase("-")){
			answer = num1 - num2;
		}
		else if(operation.equalsIgnoreCase("*")){
			num1 /= 3;
			num2 /= 3;
			
			answer = num1 * num2;
		}
		else if(operation.equalsIgnoreCase("/")){
			answer = num1 / num2;
		}
		else{
			answer = num1 + num2;
		}
		
		int[] parts = {num1, num2, answer, index};
		
		return parts;
	}
	
	public static String fromOperation(int operation){
		if(operation == 0){
			return "+";
		}
		else if(operation == 1){
			return "-";
		}
		else if(operation == 2){
			return "*";
		}
		else if(operation == 3){
			return "/";
		}
		else{
			return "+";
		}
	}
	
	public static void killPlayer(){
		JFrame f = (JFrame) MathMain.getFrames()[0];
		
		top.setVisible(false);
		middle.setVisible(false);
		bottom.setVisible(false);
		
		f.remove(top);
		f.remove(middle);
		f.remove(bottom);
		
		death.setText("Your Score Was: " + score + ", On " + difficultySelector.getSelectedItem().toString() + " mode");
		
		lost.setVisible(true);
		lostButt.setVisible(true);
		
		f.add(lost, BorderLayout.NORTH);
		f.add(lostButt, BorderLayout.CENTER);
		
		first = false;
		started = false;
		hasLost = false;
	}
}

class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == MathMain.start){
			MathMain.difficulty = MathMain.difficultySelector.getSelectedIndex();
			
			MathMain.started = true;
			
			MathMain.max = 50 * (MathMain.difficulty + 1);
			MathMain.maxTime = 1500 - ((MathMain.difficulty + 1) * 200); 
			
			MathMain.timeLeft = MathMain.maxTime;
			MathMain.timeLeftTxt.setText("Time Left: " + MathMain.maxTime);
			
			JFrame f = (JFrame) MathMain.getFrames()[0];
			
			MathMain.beforeStart.setVisible(false);
			MathMain.beforeStartOpt.setVisible(false);
			
			f.remove(MathMain.beforeStart);
			f.remove(MathMain.beforeStartOpt);
			
			f.add(MathMain.top, BorderLayout.NORTH);
			f.add(MathMain.middle, BorderLayout.CENTER);
			f.add(MathMain.bottom, BorderLayout.SOUTH);
			
			MathMain.top.setVisible(true);
			MathMain.middle.setVisible(true);
			MathMain.bottom.setVisible(true);
			
			System.out.println("Game Started!");
			
			System.out.println("Difficulty is: " + MathMain.difficulty + "/n Max Time is: " + MathMain.maxTime);
		}
		else if(e.getSource() == MathMain.checkAnswer){
			try{
				MathMain.userAnswer = Integer.parseInt(MathMain.enterAnswerBox.getText());
			}
			catch(NumberFormatException exception){
				MathMain.userAnswer = 0;
			}
			
			if(MathMain.userAnswer == MathMain.answer){
				MathMain.score++;
				MathMain.scoreTxt.setText("Score: " + MathMain.score);
				
				MathMain.timeLeft = MathMain.maxTime;
				
				MathMain.parts = MathMain.generateQuestion(MathMain.max, Constants.OP_DEF_OPERATIONS);
				
				MathMain.num1 = MathMain.parts[0];
				MathMain.num2 = MathMain.parts[1];
				MathMain.answer = MathMain.parts[2];
				
				MathMain.operationTxt.setText(MathMain.num1 + " " + MathMain.fromOperation(MathMain.parts[3]) + " " + MathMain.num2);
				
				MathMain.enterAnswerBox.setText("");
				MathMain.enterAnswerBox.requestFocus();
				
				MathMain.isCorrectTxt.setText("Correct");
				MathMain.isCorrectTxt.setBackground(Color.GREEN);
				
				MathMain.isCorrect = true;
			}
			else{
				MathMain.enterAnswerBox.setText("");
				MathMain.enterAnswerBox.requestFocus();
				
				MathMain.timeLeft -= (MathMain.difficulty * 300);
				MathMain.isCorrectTxt.setText("Incorrect");
				MathMain.isCorrectTxt.setBackground(Color.RED);
				MathMain.isCorrect = false;
			}
		}
		else if(e.getSource() == MathMain.restart){
			JFrame f = (JFrame) MathMain.getFrames()[0];
			
			MathMain.lost.setVisible(false);
			MathMain.lostButt.setVisible(false);
			
			f.remove(MathMain.lost);
			f.remove(MathMain.lostButt);
			
			f.add(MathMain.beforeStart, BorderLayout.NORTH);
			f.add(MathMain.beforeStartOpt);	
			
			MathMain.beforeStart.setVisible(true);
			MathMain.beforeStartOpt.setVisible(true);
			
			MathMain.score = 0;
			
			System.out.println("Restarting");
		}
	}
	
}
