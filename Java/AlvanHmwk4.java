import java.io.*;

//Fixed by TLWv2
//email me now
// ask alvan for it
//dont delete comments


public class AlvanHmwk4{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
  static int startingNumber = 0;
  static int increment = 0;
  static int endingNumber = 0;
  
  public static void main(String[] args){
    out("Welcome to the skip count program");
    
    out("Please enter the starting number");
    startingNumber = toInt(getUserInput());
    
    out("Now enter the increment");
    increment = toInt(getUserInput());
    
    out("Now enter the ending number");
    endingNumber = toInt(getUserInput());
    
    for(int i = startingNumber; i <= endingNumber; i += increment){
      System.out.println(i);
      //cannot cast from int to String
    }
    
    out("Would you like to do it again?");
    String s = getUserInput();
    
    if(s.equalsIgnoreCase("yes")){
      restart();
    }
    else if(s.equalsIgnoreCase("no")){
      out("ok then");
    }
    else{
      out("invalid choice");
    }
    
  }
  
  public static void out(String input){
    System.out.println(input);
  }
  
  public static int toInt(String input){
    return Integer.parseInt(input);
  }
  
  public static String getUserInput(){
    String s;
    
    try{
      s = br.readLine();
    }
    catch(IOException e){
      out("u cant do dat");
      s = "NUL";
    }
    
    return s;
  }
  
  public static void restart(){
    startingNumber = 0;
    endingNumber = 0;
    increment = 0;
    
    main(null);
  }
}

