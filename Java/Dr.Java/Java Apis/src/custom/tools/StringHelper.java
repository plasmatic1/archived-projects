package custom.tools;

public class StringHelper {
	
	public static char[] stringToArray(String input){
		char[] finalArray = new char[input.length()];
	
		for(int i = 0; i < input.length(); i++){
			finalArray[i] = input.charAt(i);
		}

		return finalArray;
	}
	
	public static String arrayToString(char[] input){
		String finalString = "";
		
		for(int i = 0; i < input.length; i++){
			finalString += input[i];
		}
		
		return finalString;
	}
	
	public static String replaceAll(String input, char toBeReplaced, char replacedWith){
		StringHelper sh = new StringHelper();
		char[] tempChar = sh.stringToArray(input);
		
		for(int i = 0; i < tempChar.length; i++){
			if(tempChar[i] == toBeReplaced){
				tempChar[i] = replacedWith;
			}
		}
		
		String finalString = StringHelper.arrayToString(tempChar);
		
		return finalString;
	}
	
	public static void generateRandomChar(){
		
		generateRandomChar(1, "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,./<>?;\"\'\\][}{()_");
	}
	
	public static String generateRandomChar(int amount, String characters){
		char[] tempArray = stringToArray(characters);
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
