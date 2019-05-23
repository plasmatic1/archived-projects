import java.io.*;

public class PasswordInterface extends PasswordGeneratorCode{
  public static void main(String [] args)throws IOException{
    int i = 1;
    while(i < 100){
    requestPassword();
    returnPassword(passwordLength);
    System.out.println("Your Password is: " + password);
    password = "";
    }
  }
}