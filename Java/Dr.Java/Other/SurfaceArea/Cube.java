public class Cube{
  private double SurfaceArea;
  public Cube(double side){
    SurfaceArea = 6 * (side * side);
  }
  
  public void getSurfaceAreaCube(){
    System.out.println("The surface area is: " + SurfaceArea + " units squared");
  }
}