package test.randomiser;

import java.util.*;

public class Randomiser {
	public static ArrayList items = new ArrayList();
	public static Scanner userInput = new Scanner(System.in);
	public static StringHelper sh = new StringHelper();
	
	public static void main(String[] args){
		getUserInput();
	}
	
	public static String getUserInput(){
		String output = "";
		
		StringHelper.fill('-', 160);
		out("Welcome to the Randomiser");
		out("Version: Simplistic Text v1.0");
		out("Here are your options");
		out("add - adds a item to the list");
		out("remove - removes a item on the list");
		out("get - gets a random choice");
		
		try{
			if(userInput.hasNextLine()){
				output = userInput.nextLine();
			}
		}
		
		finally{
			return output;
		}
		
		catch (IOException e){
			out("That isn't a valid entry, please retype it");
		}
	}
	
	public static void out(String input){
		System.out.println(input);
	}
}
