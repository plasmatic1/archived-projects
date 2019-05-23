package test.randomiser;

import java.util.*;

public class StringHelper {
	public StringHelper sh = new StringHelper();
	
	public void StringHelper(){
		
	}
	
	public char[] stringToArray(String input){
		char[] finalArray = new char[input.length()];
	
		for(int i = 0; i < input.length(); i++){
			finalArray[i] = input.charAt(i);
		}

		return finalArray;
	}
	
	public String arrayToString(char[] input){
		String finalString = "";
		
		for(int i = 0; i < input.length; i++){
			finalString += input[i];
		}
		
		return finalString;
	}
	
	public String replaceAll(String input, char toBeReplaced, char replacedWith){
		StringHelper sh = new StringHelper();
		char[] tempChar = sh.stringToArray(input);
		
		for(int i = 0; i < tempChar.length; i++){
			if(tempChar[i] == toBeReplaced){
				tempChar[i] = replacedWith;
			}
		}
		
		String finalString = sh.arrayToString(tempChar);
		
		return finalString;
	}
	
	public void generateRandomChar(){
		
		generateRandomChar(1, "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,./<>?;\"\'\\][}{()_");
	}
	
	public String generateRandomChar(int amount, String characters){
		char[] tempArray = sh.stringToArray(characters);
		String finalString = "";
		int randomNumber = 0;
		
		for(int i = 0; i < amount; i++){
			randomNumber = (int) (Math.random()*tempArray.length);
			finalString += tempArray[randomNumber];
		}
		
		return finalString;
	}
	
	public static void fill(char fillChar, int fillAmount){
		String outputString = "";
		
		for(int i = 1; i <= fillAmount; i++){
			outputString += fillChar;
		}
		
		System.out.println(outputString);
	}
}
