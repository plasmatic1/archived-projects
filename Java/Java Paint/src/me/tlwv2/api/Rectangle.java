package me.tlwv2.api;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Rectangle extends JComponent{
	private int x;
	private int y;
	private int xSize;
	private int ySize;
	private ColorScheme scheme;
	
	public Rectangle(int x, int y, int xSize, int ySize, ColorScheme scheme){
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.scheme = scheme;
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(scheme.getStroke());
		g2d.setBackground(scheme.getFill());
		
		//g2d.fillRect(x, y, xSize, ySize);
		
		g2d.draw(new Rectangle2D.Double(x, y, xSize, ySize));
	}
}
