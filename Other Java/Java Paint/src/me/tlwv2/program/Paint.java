package me.tlwv2.program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.tlwv2.api.ColorScheme;
import me.tlwv2.api.Coordinates;
import me.tlwv2.api.Rectangle;

@SuppressWarnings("serial")
public class Paint extends JFrame implements MouseListener{
	public static Paint frame;
	
	public static int sizeX;
	public static int sizeY;
	
	public static JPanel panel = new JPanel();
	
	ColorScheme cs = new ColorScheme(Color.BLUE, Color.BLACK, 5);
	
	Coordinates pos1 = new Coordinates(0, 0);
	
	JTextField text = new JTextField();
	JTextField sizeXTxt = new JTextField();
	JTextField sizeYTxt = new JTextField();
	
	
	public static void main(String[] args){
		frame = new Paint();
	}
	
	public Paint(){
		panel.setLayout(new BorderLayout(10, 10));		
		panel.setBounds(0, 0, 50, 100);
		panel.add(new Rectangle(100, 100, 50 ,50 ,cs));
		int[] moveamt = new int[2];
		moveamt[0] = -50;
		moveamt[1] = -100;
		
		this.setSize(500, 500);
		this.setTitle("Paint Program");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		text.setText("cheese");
		text.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		text.setEditable(false);
		
		sizeXTxt.setText("Insert size X here");
		sizeXTxt.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		sizeXTxt.setEditable(true);
		
		sizeYTxt.setText("Insert size Y here");
		sizeYTxt.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
		sizeYTxt.setEditable(true);
		
		//this.add(text, BorderLayout.NORTH);
		this.add(new Rectangle(100, 100, 50, 50, cs), BorderLayout.CENTER);
		this.add(panel);
		
		panel.add(sizeXTxt, BorderLayout.PAGE_START);
		panel.add(sizeYTxt, BorderLayout.AFTER_LAST_LINE);
		
		sizeXTxt.setMaximumSize(new Dimension(50, 500));
		sizeYTxt.setMaximumSize(new Dimension(50, 500));
		
		sizeXTxt.setLocation(0, sizeXTxt.getY() + moveamt[0]);
		sizeYTxt.setLocation(0, sizeYTxt.getY() + moveamt[1]);
		
		System.out.println(moveamt[0] + ", " + moveamt[1]);
		
		this.setVisible(true);
		
		this.addMouseListener(this);
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		text.setText("You clicked on: " + e.getX() + " " + e.getY());
		
		pos1.setCoordsInt(e.getX(), e.getY()); 
		
		sizeX = Integer.parseInt(sizeXTxt.getText());
		sizeY = Integer.parseInt(sizeYTxt.getText());
		
		frame.add(new Rectangle(pos1.getXInt(), pos1.getYInt(), sizeX, sizeY, cs));
		frame.setVisible(true);
			
		System.out.println("Tried to create rectangle at: " + e.getX() + " " + e.getY());
		System.out.println("With size: " + sizeX + " " + sizeY);
			
		pos1.setCoordsInt(0, 0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
