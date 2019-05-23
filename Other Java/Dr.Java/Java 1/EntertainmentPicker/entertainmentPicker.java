import java.io.*;

public class entertainmentPicker{

  public static void main(String [] args) throws IOException{
    
    String answer;
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Would you like a Movie or a TV show");
    answer = br.readLine();
    answer = answer.toLowerCase();
    
    if(answer.equals("Movie")){
      System.out.println("which movie category would you like to choose?");
      System.out.println("action");
      System.out.println("comedy");
      answer = "";
      
      answer = br.readLine();
      answer = answer.toLowerCase();
      
      if(answer.equals("action")){
        System.out.println("Here is your action movie");
        System.out.println("https://www.youtube.com/results?search_query=action+movie");
      }
      else if(answer.equals("comedy")){
        System.out.println("Here is your comedy movie");
        System.out.println("https://www.youtube.com/results?search_query=comedy+movie");
      }
      else{
        System.out.println("You should go to a psychologist");
      }
    }
    else if(answer.equals("tv show")){
      System.out.println("Which category would you like would you like?");
      System.out.println("romantic");
      System.out.println("educational");
      answer = "";
      
      answer = br.readLine();
      answer = answer.toLowerCase();
      
      if(answer.equals("coke")){
        System.out.println("Here is your show:");
        System.out.println("https://www.youtube.com/results?search_query=soap+opera+tv+show");
      }
      else if(answer.equals("sprite")){
        System.out.println("Here is your show:");
        System.out.println("https://www.youtube.com/results?search_query=national+geographic");
      }
      else{
        System.out.println("you should go to a psychologist");
      }
    }
    else{
      System.out.println("You should see a psychologist");
    }
  }
}