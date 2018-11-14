package me.tlwv2.tools.string;

public class Strlib {
	public static String[] strToArray(String msg){
		String[] end = new String[msg.length()];
		
		for(int i = 0; i < msg.length(); i++){
			end[i] = String.valueOf(msg.charAt(i));
		}
		
		return end;
	}
	
	public static String arrayToString(String[] arr){
		String endStr = "";
		
		for(String s : arr){
			endStr += s;
		}
		
		return endStr;
	}
	
	public static String getLib(CharacterLibrary lib){
		switch(lib){
			case ABC_LOWER: 
				return "abcdefghijklmnopqrstuvwxyz";
			
			case ABC_UPPER:
				return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				
			case NUM:
				return "1234567890";
				
			case BASIC:{
				String s = "";
			}
			
			return "";
		}
	}
}
