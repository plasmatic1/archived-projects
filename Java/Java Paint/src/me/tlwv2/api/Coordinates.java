package me.tlwv2.api;

public class Coordinates {
	private double x;
	private double y;
	
	public Coordinates(double X, double Y) {
		x = X;
		y = Y;
	}
	
	public Coordinates(int x, int y){
		this.x = (double) x;
		this.y = (double) y;
	}
	
	public void setCoords(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setCoordsInt(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public int getXInt(){
		return (int) x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getYInt(){
		return (int) y;
	}

}
