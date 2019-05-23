import java.io.*;

public class FileSystems{
  public static String inp;
  public static String directory;
  public static String retDir;
  
  public static void main(String [] args)throws IOException{
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader getDir = new BufferedReader(new FileReader("/files/keepDirectory.config"));
    
    if(getDir.readLine().equals("directory")){
      retDir = getDir.readLine();
      try{
        File Code = new File(retDir);
      }
      FileReader read = new FileReader(retDir);
      BufferedReader readFile = new BufferedReader(read);
    }
    
    System.out.println("Input the action");
    inp = input.readLine();
    
    if(inp.equals("create file")){
      System.out.println("What is the name of the File?");
      
      inp = input.readLine();  
      directory = "/files/" + inp + ".txt";
      
      
      
      Read();
    }
  }
  
  public static void Read(){
    while(readFile.readLine() != null){
      System.out.println(readFile.readLine());
    }
  }
}