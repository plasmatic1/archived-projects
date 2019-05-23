package me.tlwv2.api;

public class CondEquations {
	
	public static int difference(int n1, int n2){
		return (int) (difference((double) (n1), (double) (n2)));
	}
	
	public static double difference(double n1, double n2){
		double d = 0.0;
		
		if(n1 > n2){
			d = n1 - n2;
		}
		else if(n1 < n2){
			d = n2 - n1;
		}
		else if(n1 == n2){
			d = (double) 0;
		}
		
		return d;
	}

}
