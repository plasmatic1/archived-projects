import java.io.*;

public class printText{
  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int intnum;
    intnum  = Integer.parseInt(br.readLine());
      
      
    System.out.println(intnum);
      if(intnum >= 500){
      System.out.println("This number is greater or equal to 500");
      }else if(intnum <= 500){
        System.out.println("This number is less than 500");
      }else{
        System.out.println("ERROR 01: Incorrect Value Type");
      }
  }
}

// && is and, and || is or 