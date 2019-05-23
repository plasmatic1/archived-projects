import java.io.*;

public class grader{
  public static void main(String [] args) throws IOException{
    System.out.println("Put your mark in precentage here:");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int markp;
    markp = Integer.parseInt(br.readLine());
    
    if(markp <= 59){
      System.out.println("You Failed (F)");
    }
    else if(markp >= 60 && markp <= 69){
      System.out.println("Woah! You clutched it! (D)");
    }
    else if(markp >= 70 && markp <= 79){
      System.out.println("Well, it's kinda bad, well not really... (C)");
    }
    else if(markp >= 80 && markp <= 89){
      System.out.println("Up to classroom standards!!! (B)");
    }
    else if(markp >= 90 && markp <= 99){
      System.out.println("Nice mark you got there! (A)");
    }
    else if(markp == 100){
      System.out.println("Hhow??? Howd you get a perfect mark??? (A+)");
    }
    else if(markp > 100){
      System.out.println("LIARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR!!!!!!!!!!!!!!!!");
    }
  }
}