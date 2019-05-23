import java.io.*;

public class main{
  public static double length;
  public static double height;
  public static double width;
  public static double width2;
  public static double base;
  public static double radius;
  public static double side;
  public static String action;
  public static void main(String [] args)throws IOException{
    
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    for(int i = 1; i > 0; i++){
      ln("What kind of shape do you want the surface area of???");
      ln("Triangular Prism");
      ln("Cube");
      
      ln("please the proper words WITH SPACES when entering your action");
      
      action = input.readLine();
      action = action.toLowerCase();
      
      if(action.equals("triangular prism")){
        ln("Just enter all required parameters");
        ln("the machine will calculate all possible triangle types");
        ln("base is:");
        base = Double.parseDouble(input.readLine());
        ln("height is:");
        height = Double.parseDouble(input.readLine());
        ln("length is:");
        length = Double.parseDouble(input.readLine());
        ln("width is:");
        width = Double.parseDouble(input.readLine());
        ln("second width is:");
        width2 = Double.parseDouble(input.readLine());
        
        TriangularPrism TPrism = new TriangularPrism(base, height, length, width, width2);
        ln("Surface areas are");
        TPrism.getSurfaceAreaTPrism();
        action = "";
        ln("");
        ln("-------------");
        ln("");
      }
      else if(action.equals("cube")){
        ln("please enter the side length");
        side = Double.parseDouble(input.readLine());
        Cube cube = new Cube(side);
        
        cube.getSurfaceAreaCube();
        ln("");
        ln("----------------------");
        ln("");
        action = "";
    }
  }
 }
  
  public static void ln(String inp){
    System.out.println(inp);
  }
}