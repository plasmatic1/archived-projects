import java.io.*;

public class PasswordGeneratorCode{
  public static String password;
  public static int passwordLength;
  public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  public static int processDig;
  public static void main(String [] args)throws IOException{
    
  }
  
  public static void requestPassword()throws IOException{
    System.out.println("Welcome to the password generator v1.0!");
    System.out.println("Select your password length");
    passwordLength = Integer.parseInt(input.readLine());
  }
  
  public static void returnPassword(int pLength){
    while(pLength > 0){
      processDig = (int) (Math.random()*10);
      password = password + processDig;
      pLength--;
    }
  }
}
    