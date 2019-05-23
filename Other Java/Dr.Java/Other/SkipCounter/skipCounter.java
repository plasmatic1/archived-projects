import java.io.*;

public class skipCounter{
  public static void main(String [] args)throws IOException{
    double start;
    double end;
    double countBy;
    Boolean typedAlready = false;
    
    System.out.println("Welcome to the counting machine v1.0!");
    System.out.println("What number do you want to start counting at");
    
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    start = Double.parseDouble(input.readLine());
    
    System.out.println("What number do you want to end counting at");
    end = Double.parseDouble(input.readLine());
    
    System.out.println("What number do you want to count by???");
    countBy = Double.parseDouble(input.readLine());
    
    while((start < end && countBy > 0) || (countBy < 0 && start > end)){
      start += countBy;
      typedAlready = true;
      if((start > end && countBy > 0) ^ (countBy < 0 && start < end)){
        System.out.println(start);
        System.out.println("There was an error, the start was not able to match the end");
      }
      else if((start < end && countBy > 0) ^ start == end ^ (countBy < 0 && start > end)){
        System.out.println(start);
      }
    }
    if(countBy >= 0){
      if(start > end && typedAlready == false){
        System.out.println("You are seriously stupid");
      }
    }
    else if(countBy < 0){
      if(start < end && typedAlready == false){
        System.out.println("You are seriously stupid");
      }
   }
  }
}