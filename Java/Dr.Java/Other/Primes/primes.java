import java.io.*;

public class primes{
  public static int[0] numbers = {};
  public static String action;
  public static int length;
  public static int dividedInt;
  public static int checker = 1;
  public static int on = 0;
  
  public static void main(String [] args)throws IOException{
    System.out.println(primes.length);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    length = Integer.parseInt(br.readLine());
    dividedInt = Integer.parseInt(br.readLine());
    
    while(numbers.length < length){
      while(checker % dividedInt == 0){
        numbers.add(checker);
        ln("added new number!");
      }
      checker++;
    }
    
    while(on < numbers.length){
      System.out.println(numbers[on] + " at number " + on);
      on++;
    }
  }
  
  public void ln(String inp){
    System.out.println(inp);
  }
}