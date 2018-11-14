package me.tlwv2.api;

import java.awt.Color;

public class ColorScheme {
	private Color fill;
	private Color stroke;
	private int strokeSize;
	
	public ColorScheme(Color fill, Color stroke, int size){
		this.fill = fill;
		this.stroke = stroke;
		strokeSize = size;
	}
	
	public Color getFill(){
		return fill;
	}
	
	public Color getStroke(){
		return stroke;
	}
	
	public int getStrokeSize(){
		return strokeSize;
	}
	
	public void setFill(Color fill){
		this.fill = fill;
	}
	
	public void setStroke(Color stroke){
		this.stroke = stroke;
	}
	
	public void setStrokeSize(int size){
		strokeSize = size;
	}
}
