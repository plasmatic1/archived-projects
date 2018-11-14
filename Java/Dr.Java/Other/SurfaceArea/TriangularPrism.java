public class TriangularPrism{
  private double SurfaceAreaTPrismE;
  private double SurfaceAreaTPrismI;
  private double SurfaceAreaTPrismR;
  private double SurfaceAreaTPrismS;
  public TriangularPrism(double base, double height, double length, double width, double widthT){
    SurfaceAreaTPrismR = base * height + length * width + length * base + height * length;
    SurfaceAreaTPrismS = base * height + length * width + length * base + widthT * length;
    SurfaceAreaTPrismE = base * height + 3 * (length * base);
    SurfaceAreaTPrismI = base * height + 2 * width * length + base * length;
  }

  public void getSurfaceAreaTPrism(){
    System.out.println("Has Equiladerial Triangles: " + SurfaceAreaTPrismE + " units squared");
    System.out.println("Has Icoseles Triangles: " + SurfaceAreaTPrismI + " units squared");
    System.out.println("Has Right Triangles: " + SurfaceAreaTPrismR + " units squared");
    System.out.println("Has Scalene Triangles: " + SurfaceAreaTPrismS + " units squared");
  }
}